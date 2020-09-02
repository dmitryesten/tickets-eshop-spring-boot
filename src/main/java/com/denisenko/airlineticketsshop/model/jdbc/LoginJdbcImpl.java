package com.denisenko.airlineticketsshop.model.jdbc;

import com.denisenko.airlineticketsshop.config.DataSourceConfig;
import com.denisenko.airlineticketsshop.model.entity.*;
import com.denisenko.airlineticketsshop.service.exception.InvalidObjectFactoryException;
import com.denisenko.airlineticketsshop.service.exception.database.ConnectionNotSetException;
import com.denisenko.airlineticketsshop.service.exception.database.CrudOperationNotExecuteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@Qualifier("loginJdbcImpl")
public class LoginJdbcImpl implements ILoginDao<User> {

    @Autowired
    private DataSourceConfig dataSourceConfig;

    @Override
    public User getDataLogin(Login login) throws SQLException, InvalidObjectFactoryException {
        String querySelect =
            "select usr.*, lg.login, lg.password from login_user lg " +
            "join user usr on usr.id_login = lg.id \n" +
            "where lg.login = ? and lg.password = ?";

        Administrator administrator = null;
        Client client = null;
        User userFactory = null;
        try(Connection connection = dataSourceConfig.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(querySelect)){
            preparedStatement.setString(1, login.getLogin());
            preparedStatement.setString(2, login.getPassword());

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String userType = resultSet.getString("USER_TYPE").trim().toUpperCase();
                System.out.println("UserType = "+ userType);
                    userFactory = UserFactoryEntity.getFactory(userType);
                    userFactory.setUserType(userType);
                    userFactory.setId(resultSet.getLong("ID"));
                    userFactory.setFirstName(resultSet.getString("FIRST_NAME"));
                    userFactory.setLastName(resultSet.getString("LAST_NAME"));
                    userFactory.setPatronymicName(resultSet.getString("PATRONYMIC_NAME"));
                    userFactory.setLoginObject(new Login(resultSet.getString("LOGIN"), resultSet.getString("PASSWORD")));

                if(userFactory instanceof Administrator){
                    administrator = (Administrator) userFactory;
                    administrator.setPosition(resultSet.getString("POSITION"));
                } else if (userFactory instanceof Client) {
                    client = (Client) userFactory;
                    client.setPhoneClient(resultSet.getString("PHONE"));
                    client.setEmailClient(resultSet.getString("EMAIL"));
                }

            }

        }

        return userFactory;
    }

    @Override
    public boolean checkExistLogin(Login login) {
        boolean result = false;
        String sql = "select 1 from dual where exists (select id from login_user lu where lu.login = ? )";
        try(Connection connection = dataSourceConfig.getDataSource().getConnection()) {
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1, login.getLogin());
                ResultSet resultSet = preparedStatement.executeQuery();
                result = resultSet.next();
            } catch (SQLException e){
                throw new CrudOperationNotExecuteException("Невозможно выполнить запрос",e);
            }
        } catch (SQLException e) {
            throw new ConnectionNotSetException("Невозможно установить соединение с базой данных", e);
        }
        return result;
    }

}
