package com.denisenko.airlineticketsshop.model.jdbc;

import com.denisenko.airlineticketsshop.config.DataSourceConfig;
import com.denisenko.airlineticketsshop.service.exception.database.ConnectionNotSetException;
import com.denisenko.airlineticketsshop.service.exception.database.CrudOperationNotExecuteException;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CookieJdbcImpl implements ICookieJdbc {

    @Autowired
    DataSourceConfig dataSourceConfig;

    @Override
    public void delete(String cookie) throws SQLException {
        String deleteQuery = "delete from cookie_login ck where UPPER(ck.value_cookie) = UPPER(?)";

        try(Connection connection = dataSourceConfig.getDataSource().getConnection()) {

            try(PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)){
                preparedStatement.setString(1, cookie);
                preparedStatement.executeUpdate();
            }catch (SQLException e){
                throw new CrudOperationNotExecuteException("DELETE operation could not execute", e);
            }

        } catch (SQLException e){
            throw new ConnectionNotSetException("Connection has not set with the database", e);
        }

    }
}
