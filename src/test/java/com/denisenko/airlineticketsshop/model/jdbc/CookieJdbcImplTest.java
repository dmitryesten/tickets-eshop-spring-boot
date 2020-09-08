package com.denisenko.airlineticketsshop.model.jdbc;

import com.denisenko.airlineticketsshop.config.AppConfiguration;
import com.denisenko.airlineticketsshop.model.entity.CookieLogin;
import com.denisenko.airlineticketsshop.model.entity.Login;
import org.junit.Assert;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import javax.servlet.http.Cookie;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({AppConfiguration.class})
@JdbcTest
class CookieJdbcImplTest {

    @Autowired
    private ICookieDao cookieDao;

    private static long actualId;
    private Login testLogin = new Login(2, "Test2", "Paswor123");
    private Cookie testCookie = new Cookie("JAVASESSIONID", "test-value-01");

    @Test
    @Order(3)
    void delete() throws SQLException {
        cookieDao.delete(testCookie);
    }

    @Test
    @Order(2)
    void update(){
        testCookie.setValue("test-value-01");
        cookieDao.update(testLogin, testCookie);
    }

    @Test
    @Order(1)
    void create() {
        testCookie.setMaxAge(7 * 24 * 60 * 60);
        testCookie.setPath("/");
        testCookie.setSecure(false);
        testCookie.setHttpOnly(false);
        testCookie.setHttpOnly(true);

        CookieLogin cookieLogin = cookieDao.create(testLogin, testCookie);
        System.out.println(cookieLogin.getId());
        Assert.assertNotEquals(0L, cookieLogin.getId());
    }

}