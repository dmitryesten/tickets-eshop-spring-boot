package com.denisenko.airlineticketsshop.model.jdbc;

import com.denisenko.airlineticketsshop.config.DataSourceConfig;
import com.denisenko.airlineticketsshop.model.entity.*;
import com.denisenko.airlineticketsshop.service.exception.InvalidObjectFactoryException;
import com.denisenko.airlineticketsshop.service.exception.database.ConnectionNotSetException;
import com.denisenko.airlineticketsshop.service.exception.database.CrudOperationNotExecuteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import java.sql.*;

@Repository
@Qualifier("userJdbc")
public class UserJdbcImpl implements IUserDao<User> {

    @Autowired
    private DataSourceConfig dataSourceConfig;

    @Override
    public User create(User user) throws SQLException {
        String insertLogin = "INSERT INTO login_user (id, login, password) VALUES (seq_id.nextval, ?, ?)";
        String insertUser = "INSERT INTO user (id, id_login, first_name, last_name, patronymic_name, user_type, position, email, phone) VALUES (seq_id.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
        User userTemporary = user;
        try (Connection connection = dataSourceConfig.getDataSource().getConnection();
             PreparedStatement preparedStatementLogin = connection.prepareStatement(insertLogin, Statement.RETURN_GENERATED_KEYS);) {

            preparedStatementLogin.setString(1, user.getLoginObject().getLogin());
            preparedStatementLogin.setString(2, user.getLoginObject().getPassword());
            preparedStatementLogin.executeUpdate();

            userTemporary.setLoginObject(new Login(getPrimaryKey(preparedStatementLogin), user.getLoginObject().getLogin(), user.getLoginObject().getPassword()));

            try (PreparedStatement preparedStatementUser = connection.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatementUser.setLong(1, userTemporary.getLoginObject().getId());
                preparedStatementUser.setString(2, userTemporary.getFirstName());
                preparedStatementUser.setString(3, userTemporary.getLastName());
                preparedStatementUser.setString(4, userTemporary.getPatronymicName());
                preparedStatementUser.setString(5, userTemporary.getUserType().getTypeEntityString());
                if (user instanceof Administrator) {
                    Administrator administrator = (Administrator) userTemporary;
                    preparedStatementUser.setString(6, administrator.getPosition());
                } else preparedStatementUser.setString(6, null);

                if (user instanceof Client) {
                    Client client = (Client) userTemporary;
                    preparedStatementUser.setString(7, client.getEmail());
                    preparedStatementUser.setString(8, client.getPhone());
                } else {
                    preparedStatementUser.setString(7, null);
                    preparedStatementUser.setString(8, null);
                }

                preparedStatementUser.executeUpdate();
                userTemporary.setId(getPrimaryKey(preparedStatementUser));
            }
        }
        return userTemporary;
    }

    @Override
    public User select(Cookie cookie) {
        String querySelectByCookieValue =
            "select usr.*, lg.login, lg.password from login_user lg \n" +
                "join user usr on usr.id_login = lg.id\n" +
                "join cookie_login cl on cl.id_login = lg.id\n" +
                "where cl.cookie_value = ?";
            CookieLogin cookieLogin = new CookieLogin((AppCookie) cookie);
        return select(cookieLogin, querySelectByCookieValue);
    }

    @Override
    public User select(Login login) {
        String querySelectByLogin =
            "select usr.*, lg.login, lg.password from login_user lg " +
                "join user usr on usr.id_login = lg.id \n" +
                "where lg.login = ? and lg.password = ?";
        CookieLogin cookieLogin = new CookieLogin(login);
        return select(cookieLogin, querySelectByLogin);
    }

    private PreparedStatement getPrepareStatementDynamic(Connection connection, CookieLogin cookieLogin, String query) throws SQLException {
        PreparedStatement preparedStatement = null;

        switch (StringUtils.countOccurrencesOf(query, "?")) {
            case 1:
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, cookieLogin.getCookie().getValue());
                break;
            case 2:
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, cookieLogin.getLogin().getLogin());
                preparedStatement.setString(2, cookieLogin.getLogin().getPassword());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + StringUtils.countOccurrencesOf(query, "?"));
        }
        return preparedStatement;
    }

    private User select(CookieLogin cookieLogin, String query) {
        Administrator administrator = null;
        Client client = null;
        User userFactory = null;
        try (Connection connection = dataSourceConfig.getDataSource().getConnection()) {

            try (PreparedStatement preparedStatement = getPrepareStatementDynamic(connection, cookieLogin, query)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String userType = resultSet.getString("USER_TYPE").trim().toUpperCase();
                    userFactory = UserFactoryEntity.getFactory(userType);
                    userFactory.setUserType(userType);
                    userFactory.setId(resultSet.getLong("ID"));
                    userFactory.setFirstName(resultSet.getString("FIRST_NAME"));
                    userFactory.setLastName(resultSet.getString("LAST_NAME"));
                    userFactory.setPatronymicName(resultSet.getString("PATRONYMIC_NAME"));
                    userFactory.setLoginObject(new Login(resultSet.getLong("ID_LOGIN"), resultSet.getString("LOGIN"), resultSet.getString("PASSWORD")));

                    if (userFactory instanceof Administrator) {
                        administrator = (Administrator) userFactory;
                        administrator.setPosition(resultSet.getString("POSITION"));
                    } else if (userFactory instanceof Client) {
                        client = (Client) userFactory;
                        client.setPhoneClient(resultSet.getString("PHONE"));
                        client.setEmailClient(resultSet.getString("EMAIL"));
                    }
                }
            } catch (InvalidObjectFactoryException e) {
                throw new CrudOperationNotExecuteException("SELECT operation could not execute", e);
            }

        } catch (SQLException e) {
            throw new ConnectionNotSetException("Service has not connection to the database", e);
        }
        return userFactory;

    }
}
