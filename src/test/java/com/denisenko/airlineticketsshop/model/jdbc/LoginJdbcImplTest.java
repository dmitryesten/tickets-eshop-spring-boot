package com.denisenko.airlineticketsshop.model.jdbc;

import com.denisenko.airlineticketsshop.config.AppConfiguration;
import com.denisenko.airlineticketsshop.model.entity.Administrator;
import com.denisenko.airlineticketsshop.model.entity.Client;
import com.denisenko.airlineticketsshop.model.entity.Login;
import com.denisenko.airlineticketsshop.model.entity.User;
import com.denisenko.airlineticketsshop.service.exception.InvalidObjectFactoryException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

//@RunWith(SpringRunner.class) for jUnit 4
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({AppConfiguration.class})
@JdbcTest
public class LoginJdbcImplTest {

    @Autowired
    @Qualifier("loginJdbcImpl")
    private ILoginDao<User> loginDao;


    @org.junit.jupiter.api.Test
    public void testGetDataLoginAdmin() throws InvalidObjectFactoryException, SQLException {
        Login login  = new Login("Test1", "123");
        Assert.assertNotNull(loginDao.getDataLogin(login));
    }

    @org.junit.jupiter.api.Test
    public void testGetDataLoginClient() throws InvalidObjectFactoryException, SQLException {
        Login login  = new Login("Test2", "123");
        Client user = (Client) loginDao.getDataLogin(login);
        Assert.assertNotNull(loginDao.getDataLogin(login));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Test1", "Test2"})
    public void testCheckingExistsLogin(String login) throws InvalidObjectFactoryException, SQLException {
        Login validLogin  = new Login(login, "123");
        Assert.assertTrue(loginDao.checkExistLogin(validLogin));
    }

    @ParameterizedTest
    @ValueSource(strings = {"InvalidTest1", "InvalidTest2"})
    public void testCheckingNotExistsLogin(String login) throws InvalidObjectFactoryException, SQLException {
        Login invalidLogin  = new Login(login, "123");
        Assert.assertFalse(loginDao.checkExistLogin(invalidLogin));
    }

}