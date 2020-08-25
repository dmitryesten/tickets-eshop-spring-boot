package com.denisenko.airlineticketsshop.model.jdbc;


import com.denisenko.airlineticketsshop.config.DataSourceConfig;
import com.denisenko.airlineticketsshop.model.entity.Login;
import com.denisenko.airlineticketsshop.model.entity.Administrator;
import com.denisenko.airlineticketsshop.model.entity.Client;
import com.denisenko.airlineticketsshop.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class UserCreateJdbc implements IUserCreateDao<User>  {

    @Autowired
    DataSourceConfig dataSourceConfig;

    @Override
    public User create(User user) throws SQLException {
        String insertLogin = "INSERT INTO login (id, nickname, password) VALUES (seq_id.nextval, ?, ?)";
        String insertUser = "INSERT INTO user (id, id_login, first_name, last_name, patronymic_name, user_type, position, email, phone) VALUES (seq_id.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
        User userTemporary = user;
        try(Connection connection = dataSourceConfig.getDataSourceTest().getConnection();
            PreparedStatement preparedStatementLogin = connection.prepareStatement(insertLogin, Statement.RETURN_GENERATED_KEYS);) {

            preparedStatementLogin.setString(1, user.getLogin().getNickname());
            preparedStatementLogin.setString(2,  user.getLogin().getPassword());
            preparedStatementLogin.executeUpdate();

            userTemporary.setLogin(new Login(getPrimaryKey(preparedStatementLogin), user.getLogin().getNickname(), user.getLogin().getPassword()));

            try(PreparedStatement preparedStatementUser = connection.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS)){
                preparedStatementUser.setLong(1, userTemporary.getLogin().getId());
                preparedStatementUser.setString(2, userTemporary.getFirstName());
                preparedStatementUser.setString(3, userTemporary.getLastName());
                preparedStatementUser.setString(4, userTemporary.getPatronymicName());
                preparedStatementUser.setString(5, userTemporary.getUserType().getTypeEntityString());
                if (user instanceof Administrator) {
                    Administrator administrator = (Administrator) userTemporary;
                    preparedStatementUser.setString(6, administrator.getPosition());
                } else preparedStatementUser.setString(6, null);

                if (user instanceof Client){
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



}
