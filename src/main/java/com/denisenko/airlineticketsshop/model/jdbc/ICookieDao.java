package com.denisenko.airlineticketsshop.model.jdbc;

import com.denisenko.airlineticketsshop.model.entity.CookieLogin;
import com.denisenko.airlineticketsshop.model.entity.Login;

import javax.servlet.http.Cookie;
import java.sql.SQLException;

public interface ICookieDao extends IPrimaryKey {

    void delete(Cookie cookie) throws SQLException;

    CookieLogin create(Login login, Cookie cookie);

    void update(Login login, Cookie cookie);
}
