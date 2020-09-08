package com.denisenko.airlineticketsshop.model.jdbc;

import com.denisenko.airlineticketsshop.config.DataSourceConfig;
import com.denisenko.airlineticketsshop.model.entity.CookieLogin;
import com.denisenko.airlineticketsshop.model.entity.Login;
import com.denisenko.airlineticketsshop.service.exception.database.ConnectionNotSetException;
import com.denisenko.airlineticketsshop.service.exception.database.CrudOperationNotExecuteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.http.Cookie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class CookieJdbcImpl implements ICookieDao {

    @Autowired
    DataSourceConfig dataSourceConfig;

    @Override
    public void delete(Cookie cookie) throws SQLException {
        String deleteQuery = "delete from cookie_login ck where UPPER(ck.cookie_name) = UPPER(?) and UPPER(ck.cookie_value) = UPPER(?)";

        try(Connection connection = dataSourceConfig.getDataSource().getConnection()) {

            try(PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)){
                preparedStatement.setString(1, cookie.getName());
                preparedStatement.setString(2, cookie.getValue());
                preparedStatement.executeUpdate();
            }catch (SQLException e){
                throw new CrudOperationNotExecuteException("DELETE operation could not execute", e);
            }

        } catch (SQLException e){
            throw new ConnectionNotSetException("Connection has not set with the database", e);
        }

    }

    @Override
    public CookieLogin create(Login login, Cookie cookie) {
        long id;
        String insertQuery =
            "insert into cookie_login (id_login, cookie_name, cookie_value, cookie_path, cookie_max_age, cookie_is_sercure, cookie_is_http_only) " +
                "values (?, ?, ?, ?, ?, ?, ?)";
        CookieLogin cookieLogin = null;
        try(Connection connection = dataSourceConfig.getDataSource().getConnection()) {

            try(PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)){

                preparedStatement.setLong(1, login.getId());
                preparedStatement.setString(2, cookie.getName());
                preparedStatement.setString(3, cookie.getValue());
                preparedStatement.setString(4, cookie.getPath());
                preparedStatement.setInt(5, cookie.getMaxAge());
                preparedStatement.setBoolean(6, cookie.getSecure());
                preparedStatement.setBoolean(7, cookie.isHttpOnly());
                preparedStatement.executeUpdate();

                cookieLogin = new CookieLogin(cookie);
                cookieLogin.setId(getPrimaryKey(preparedStatement));
                cookieLogin.setLogin(login);
            }catch (SQLException e){
                throw new CrudOperationNotExecuteException("INSERT operation could not execute", e);
            }

        } catch (SQLException e){
            throw new ConnectionNotSetException("Connection has not set with the database", e);
        }
        return cookieLogin;
    }

    @Override
    public void update(Login login, Cookie cookie) {
        String updateQuery = "update cookie_login set cookie_value = ? where id_login = (select u.id from login_user u where u.login = ?)";

        try(Connection connection = dataSourceConfig.getDataSource().getConnection()) {

            try(PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)){
                preparedStatement.setString(1, cookie.getValue());
                preparedStatement.setString(2, login.getLogin());
                preparedStatement.executeUpdate();
            } catch (SQLException e){
                throw new CrudOperationNotExecuteException("Update operation could not execute", e);
            }

        } catch (SQLException e) {
            throw new ConnectionNotSetException("Connection has not set with the database", e);
        }
    }

}
