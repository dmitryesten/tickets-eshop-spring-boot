package com.denisenko.airlineticketsshop.model.jdbc;

import com.denisenko.airlineticketsshop.model.Login;
import com.denisenko.airlineticketsshop.model.entity.Administrator;
import com.denisenko.airlineticketsshop.model.entity.User;

import java.sql.*;

public class AdminCreateJdbc {

    public Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:mem:airline", "sa", "");
    }
/*
    public User create(User admin) throws SQLException {
        String query = "INSERT INTO login (nickname, password) VALUES (?, ?)";
        long id;
        try(Connection connection = connect()) {

            try(PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
                Login login = new Login(admin.getLogin().getNickname(), admin.getLogin().getPassword());
                preparedStatement.setString(1, login.getNickname());
                preparedStatement.setString(2, login.getPassword());

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    // get the ID back
                    try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                        if (rs.next()) {
                            id = rs.getLong(1);
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }

        } catch (SQLException e) {
            throw new SQLException("Не удалось установить соединение с БД", e.getCause());
        }
    }
*/

}
