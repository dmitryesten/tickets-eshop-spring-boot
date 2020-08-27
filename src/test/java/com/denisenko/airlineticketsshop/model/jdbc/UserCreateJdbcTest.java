package com.denisenko.airlineticketsshop.model.jdbc;


import com.denisenko.airlineticketsshop.config.DataSourceConfig;
import com.denisenko.airlineticketsshop.model.entity.Login;
import com.denisenko.airlineticketsshop.model.entity.Administrator;
import com.denisenko.airlineticketsshop.model.entity.User;
import com.denisenko.airlineticketsshop.model.dto.request.AdminRegistrationRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@JdbcTest
@ActiveProfiles("test")
@Import({UserCreateJdbcImpl.class, DataSourceConfig.class})
@Transactional("PlatformTransactionManager")
public class UserCreateJdbcTest {

    @Autowired
    private UserCreateJdbcImpl userCreateJdbc;

    //@Autowired
    //private DataSourceConfig dataSourceConfig;

    private AdminRegistrationRequest requestAdmin;

    @Before
    public void setUp() {
        requestAdmin = new AdminRegistrationRequest(
            "AdminFirstName",
            "AdminLastName",
            "AdmiPatronymicName",
            "AdminLoginTest",
            "AdminPasswordTest",
            "adminPositionTest");
    }

    @Test
    public void createAdmin() throws SQLException {

        Administrator admin = new Administrator();
        admin.setFirstName(requestAdmin.getFirstName());
        admin.setLastName(requestAdmin.getLastName());
        admin.setPatronymicName(requestAdmin.getPatronymic());
        admin.setLoginObject(new Login(requestAdmin.getLogin(), requestAdmin.getPassword()));
        admin.setPosition(requestAdmin.getPosition());

        User userAdmin = userCreateJdbc.create(admin);
        //Assert.assertNotNull(userAdmin);
        //Assert.assertNotEquals("Проверям, что не был создан объект с дефолтными параметрами", 0, userAdmin.getId());

    }
}