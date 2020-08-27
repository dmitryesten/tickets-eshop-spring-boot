package com.denisenko.airlineticketsshop.model.jdbc;

import com.denisenko.airlineticketsshop.config.DataSourceConfig;
import com.denisenko.airlineticketsshop.model.entity.*;
import com.denisenko.airlineticketsshop.service.exception.InvalidObjectFactoryException;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginJdbcImpl implements ILoginDao<User> {

    @Autowired
    DataSourceConfig dataSourceConfig;

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
                    userFactory = UserFactory.getFactory(userType);
                    userFactory.setUserType(userType);
                    userFactory.setId(resultSet.getLong("ID"));
                    userFactory.setFirstName(resultSet.getString("FIRST_NAME"));
                    userFactory.setLastName(resultSet.getString("LAST_NAME"));
                    userFactory.setPatronymicName(resultSet.getString("PATRONYMIC_NAME"));
                    userFactory.setLoginObject(new Login(resultSet.getString("LOGIN"), resultSet.getString("PASSWORD")));

                if(userFactory instanceof Administrator){
                    //((Administrator) userFactory).setPosition(resultSet.getString("POSITION"));
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
}
