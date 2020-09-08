package com.denisenko.airlineticketsshop.controller.auth;

import com.denisenko.airlineticketsshop.config.AppConfiguration;
import com.denisenko.airlineticketsshop.model.dto.request.AdminRegistrationRequest;
import com.denisenko.airlineticketsshop.model.dto.response.AdminRegistrationResponse;
import com.denisenko.airlineticketsshop.model.entity.UserType;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(AppConfiguration.class)
public class AdminRegistrationControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private AdminRegistrationRequest requestCreateAdmin;

    @Before
    public void createRequestBodyObject(){
        requestCreateAdmin = new AdminRegistrationRequest();
            requestCreateAdmin.setFirstName("Ivan");
            requestCreateAdmin.setLastName("Ivanov");
            requestCreateAdmin.setPatronymic("Ivanovich");
            requestCreateAdmin.setPosition("Sale Manager");
            //Avoiding exception unique constraint of database
            requestCreateAdmin.setLogin("AdminLogin"+ UUID.randomUUID().toString());
            requestCreateAdmin.setPassword("password123");
    }

    @Test
    public void createAdmin() {
        ResponseEntity<AdminRegistrationResponse> actualResponse =
            this.restTemplate.postForEntity(
            "http://localhost:" + port + "/api/admin",
            requestCreateAdmin,
            AdminRegistrationResponse.class);

        Assertions.assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(actualResponse.getBody().getId()).isNotZero();
        Assertions.assertThat(actualResponse.getBody().getUserType()).isEqualTo(UserType.ADMIN.getTypeEntityString());
        Assertions.assertThat(actualResponse.getHeaders().get("Set-Cookie")).isNotEmpty();
    }

}