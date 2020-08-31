package com.denisenko.airlineticketsshop.model.jdbc;

import com.denisenko.airlineticketsshop.config.AppConfiguration;
import com.denisenko.airlineticketsshop.model.entity.Administrator;
import com.denisenko.airlineticketsshop.model.entity.Client;
import com.denisenko.airlineticketsshop.model.entity.Login;
import com.denisenko.airlineticketsshop.model.entity.User;
import com.denisenko.airlineticketsshop.service.exception.InvalidObjectFactoryException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({AppConfiguration.class})
@JdbcTest
public class LoginJdbcImplTest {

    @Autowired
    @Qualifier("loginJdbcImpl")
    private ILoginDao<User> getLogin;

    @Test
    public void getDataLoginAdmin() throws InvalidObjectFactoryException, SQLException {
        Login login  = new Login("Test1", "123");
        Administrator user = (Administrator) getLogin.getDataLogin(login);
        Assert.assertNotNull(user);
    }

    @Test
    public void getDataLoginClient() throws InvalidObjectFactoryException, SQLException {
        Login login  = new Login("Test2", "123");
        Client user = (Client) getLogin.getDataLogin(login);
        Assert.assertNotNull(user);
    }

}