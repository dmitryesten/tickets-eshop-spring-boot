package com.denisenko.airlineticketsshop.model.jdbc;

import com.denisenko.airlineticketsshop.model.entity.Login;
import com.denisenko.airlineticketsshop.model.entity.User;

import javax.servlet.http.Cookie;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IUserDao<T extends User> extends IPrimaryKey {

    T create(T user) throws SQLException;

    T select(Cookie cookie);

    T select(Login login);

}
