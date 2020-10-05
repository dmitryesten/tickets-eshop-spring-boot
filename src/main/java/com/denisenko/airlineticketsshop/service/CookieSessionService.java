package com.denisenko.airlineticketsshop.service;

import com.denisenko.airlineticketsshop.model.entity.Login;
import com.denisenko.airlineticketsshop.model.jdbc.ICookieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.sql.SQLException;

@Service
public class CookieSessionService {

    @Autowired
    @Qualifier("cookieJdbc")
    private ICookieDao cookieJdbc;

    public void deleteCookie(Cookie cookie) throws SQLException {
        cookieJdbc.delete(cookie);
    }

    public void updateCookie(Login login, Cookie cookie){
        cookieJdbc.update(login, cookie);
    }

}

