package com.denisenko.airlineticketsshop.model.jdbc;


import com.denisenko.airlineticketsshop.config.AppConfiguration;
import com.denisenko.airlineticketsshop.model.dto.request.ClientRegistrationRequest;
import com.denisenko.airlineticketsshop.model.entity.*;
import com.denisenko.airlineticketsshop.model.dto.request.AdminRegistrationRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@Import({AppConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@JdbcTest
public class UserCreateJdbcTest {

    @Autowired
    @Qualifier("userCreateJdbc")
    private IUserDao userCreateJdbc;

    private AdminRegistrationRequest requestAdmin;
    private ClientRegistrationRequest requestClient;

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
            admin.setUserType(UserType.ADMIN.getTypeEntityString());

        User userAdmin = userCreateJdbc.create(admin);
        Assert.assertNotNull(userAdmin);
        Assert.assertNotEquals("Checking that the object is created with default value", 0, userAdmin.getId());
    }

    @Test
    public void createClient() throws SQLException {
        Client client = new Client();
            client.setFirstName("Иван");
            client.setLastName("Иванов");
            client.setPatronymicName("Иванович");
            client.setEmailClient("ivan@mail.ru");
            client.setPhoneClient("+79088084042");
            client.setUserType(UserType.CLIENT);
            client.setLoginObject(new Login("client1", "123"));

        Client userClient = (Client) userCreateJdbc.create(client);
        Assert.assertNotNull(userClient);
        Assert.assertNotEquals("Checking that the object is created with default value", 0, userClient.getId());
    }

}