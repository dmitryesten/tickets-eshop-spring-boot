package com.denisenko.airlineticketsshop.model.jdbc;


import com.denisenko.airlineticketsshop.config.AppConfiguration;
import com.denisenko.airlineticketsshop.config.DataSourceConfig;
import com.denisenko.airlineticketsshop.model.entity.Login;
import com.denisenko.airlineticketsshop.model.entity.Administrator;
import com.denisenko.airlineticketsshop.model.entity.User;
import com.denisenko.airlineticketsshop.model.dto.request.AdminRegistrationRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.DataSourceFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import java.sql.SQLException;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@RunWith(SpringRunner.class)
@Import({UserCreateJdbcImpl.class, AppConfiguration.class})
@JdbcTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase
public class UserCreateJdbcTest {

    @Autowired
    private UserCreateJdbcImpl userCreateJdbc;

    @Autowired
    DataSourceConfig dataSourceConfig;

    private EmbeddedDatabase db;

    //@Autowired
    //private DataSourceConfig dataSourceConfig;

    private AdminRegistrationRequest requestAdmin;

    @Before
    public void setUp() {
        db = new EmbeddedDatabaseBuilder()
            .setType(H2)
            .setName("jdbc:h2:mem:airline;DB_CLOSE_DELAY=-1")
            .setScriptEncoding("UTF-8")
            .ignoreFailedDrops(true)
            .addScript("schema.sql")
            .build();


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
        Assert.assertNotNull(userAdmin);
        Assert.assertTrue(true);
        Assert.assertNotEquals("Проверям, что не был создан объект с дефолтными параметрами", 0, userAdmin.getId());


        dataSourceConfig.getDataSource().getConnection().prepareStatement("select 1").execute();



    }
}