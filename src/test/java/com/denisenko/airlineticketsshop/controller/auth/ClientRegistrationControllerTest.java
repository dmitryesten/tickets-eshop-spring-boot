package com.denisenko.airlineticketsshop.controller.auth;

import com.denisenko.airlineticketsshop.config.AppConfiguration;
import com.denisenko.airlineticketsshop.model.dto.request.ClientRegistrationRequest;
import com.denisenko.airlineticketsshop.model.dto.response.ClientRegistrationResponse;
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


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(AppConfiguration.class)
public class ClientRegistrationControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private ClientRegistrationRequest requestCreateClient;

    @Before
    public void createRequestBodyObject(){
        requestCreateClient = new ClientRegistrationRequest();
        requestCreateClient.setFirstName("Ivan");
        requestCreateClient.setLastName("Ivanov");
        requestCreateClient.setPatronymic("Ivanovich");
        requestCreateClient.setEmail("ivan@mail.ru");
        requestCreateClient.setPhone("+79008001020");
        requestCreateClient.setLogin("AdminLogin");
        requestCreateClient.setPassword("password123");
    }

    @Test
    public void createAdmin() {
        ResponseEntity<ClientRegistrationResponse> actualResponse =
            this.restTemplate.postForEntity(
                "http://localhost:" + port + "/api/client",
                requestCreateClient,
                ClientRegistrationResponse.class);

        Assertions.assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(actualResponse.getBody().getId()).isNotZero();
        Assertions.assertThat(actualResponse.getBody().getUserType()).isEqualTo(UserType.CLIENT.getTypeEntityString());
    }

}