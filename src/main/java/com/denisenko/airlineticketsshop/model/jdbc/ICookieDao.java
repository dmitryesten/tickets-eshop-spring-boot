package com.denisenko.airlineticketsshop.model.jdbc;

import com.denisenko.airlineticketsshop.model.entity.Login;

import java.sql.SQLException;

public interface ICookieDao extends IPrimaryKey {

    void delete(String cookie) throws SQLException;

    long create(Login login, String valueCookie);

}
