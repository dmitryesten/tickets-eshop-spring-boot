package com.denisenko.airlineticketsshop.model.jdbc;

import java.sql.SQLException;

public interface ICookieJdbc {

    void delete(String cookie) throws SQLException;

}
