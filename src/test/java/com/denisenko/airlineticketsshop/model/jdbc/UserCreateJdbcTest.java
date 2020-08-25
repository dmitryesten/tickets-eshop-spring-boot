package com.denisenko.airlineticketsshop.model.jdbc;


import com.denisenko.airlineticketsshop.model.entity.Login;
import com.denisenko.airlineticketsshop.model.entity.Administrator;
import com.denisenko.airlineticketsshop.model.entity.User;
import com.denisenko.airlineticketsshop.model.rest.request.AdminRegistrationRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;


@RunWith(SpringRunner.class)
@JdbcTest
public class UserCreateJdbcTest {

    @Autowired
    private UserCreateJdbc userCreateJdbc;

    @Before
    public void setUp() {

    }

    @Test
    public void createAdmin() throws SQLException {

        AdminRegistrationRequest requestAdmin = new AdminRegistrationRequest(
            "AdminFirstName",
            "AdminLastName",
            "AdmiPatronymicName",
            "AdminLoginTest",
            "AdminPasswordTest",
            "adminPositionTest");

        Administrator admin = new Administrator();
        admin.setFirstName(requestAdmin.getFirstName());
        admin.setLastName(requestAdmin.getLastName());
        admin.setPatronymicName(requestAdmin.getPatronymic());
        admin.setLogin(new Login(requestAdmin.getLogin(), requestAdmin.getPassword()));
        admin.setPosition(requestAdmin.getPosition());

        User userAdmin = userCreateJdbc.create(admin);
        Assert.assertNotNull(userAdmin);

    }
}