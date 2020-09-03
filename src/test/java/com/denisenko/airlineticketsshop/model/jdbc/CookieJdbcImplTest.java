package com.denisenko.airlineticketsshop.model.jdbc;

import com.denisenko.airlineticketsshop.config.AppConfiguration;
import com.denisenko.airlineticketsshop.model.entity.Login;
import org.junit.Assert;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({AppConfiguration.class})
@JdbcTest
class CookieJdbcImplTest {

    @Autowired
    private ICookieDao cookieDao;

    private static long actualId;

    @Test
    @Order(2)
    void delete() throws SQLException {
        cookieDao.delete("test-value");
    }

    @Test
    @Order(1)
    void create() {
        Login testLogin = new Login(2, "Test2", "Paswor123");
        actualId = cookieDao.create(testLogin, "test-value");
        Assert.assertNotEquals(0L, actualId);
    }

}