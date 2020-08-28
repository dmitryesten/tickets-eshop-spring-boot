package com.denisenko.airlineticketsshop.service;

import com.denisenko.airlineticketsshop.model.jdbc.ICookieJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class CookieSessionService {

    @Autowired
    private ICookieJdbc cookieJdbc;

    public void deleteCookie(String cookie) throws SQLException {
        cookieJdbc.delete(cookie);
    }

}
