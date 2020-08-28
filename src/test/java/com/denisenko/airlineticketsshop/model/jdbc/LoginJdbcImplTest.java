package com.denisenko.airlineticketsshop.model.jdbc;

import com.denisenko.airlineticketsshop.config.AppConfiguration;
import com.denisenko.airlineticketsshop.model.entity.Login;
import com.denisenko.airlineticketsshop.service.exception.InvalidObjectFactoryException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@JdbcTest
@Import({LoginJdbcImpl.class, AppConfiguration.class})
public class LoginJdbcImplTest {

    @Autowired
    private ILoginDao<Login> getLogin;

    @Test
    public void getDataLogin() throws InvalidObjectFactoryException, SQLException {
        Login login  = new Login("Test1", "123");
        getLogin.getDataLogin(login);
    }

}