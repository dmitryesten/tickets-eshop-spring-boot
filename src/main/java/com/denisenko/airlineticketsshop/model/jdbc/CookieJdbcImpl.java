package com.denisenko.airlineticketsshop.model.jdbc;

import com.denisenko.airlineticketsshop.config.DataSourceConfig;
import com.denisenko.airlineticketsshop.model.entity.Login;
import com.denisenko.airlineticketsshop.service.exception.database.ConnectionNotSetException;
import com.denisenko.airlineticketsshop.service.exception.database.CrudOperationNotExecuteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class CookieJdbcImpl implements ICookieDao {

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

    @Override
    public long create(Login login, String valueCookie) {
        long id;
        String insertQuery = "insert into cookie_login (id_login, value_cookie) values (?, ?)";
        try(Connection connection = dataSourceConfig.getDataSource().getConnection()) {

            try(PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setLong(1, login.getId());
                preparedStatement.setString(2, valueCookie);
                preparedStatement.executeUpdate();
                id = getPrimaryKey(preparedStatement);
            }catch (SQLException e){
                throw new CrudOperationNotExecuteException("INSERT operation could not execute", e);
            }

        } catch (SQLException e){
            throw new ConnectionNotSetException("Connection has not set with the database", e);
        }
        return id;
    }
}
