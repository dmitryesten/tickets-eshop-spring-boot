package com.denisenko.airlineticketsshop.controller.auth;

import com.denisenko.airlineticketsshop.config.AppConfiguration;
import com.denisenko.airlineticketsshop.model.dto.request.ClientRegistrationRequest;
import com.denisenko.airlineticketsshop.model.dto.response.ClientRegistrationResponse;
import com.denisenko.airlineticketsshop.model.entity.Client;
import com.denisenko.airlineticketsshop.model.entity.UserType;
import com.denisenko.airlineticketsshop.model.jdbc.IUserCreateDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(AppConfiguration.class)
class ClientRegistrationControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    private ClientRegistrationRequest request;
    private ClientRegistrationResponse response;

    @BeforeEach
    public void setUp() throws Exception {
       request = new ClientRegistrationRequest();
       request.setFirstName("A");
       request.setLastName("A");
       request.setPatronymic("S");
       request.setLogin("W");
       request.setPassword("123");
       request.setPhone("7");
       request.setEmail("i@mail.ru");
       response = ClientRegistrationResponse.builder()
           .setId(103L)
           .setFirstName(request.getFirstName())
           .setLastName(request.getLastName())
           .setPatronymicName(request.getPatronymic())
           .setUserType(UserType.CLIENT)
           .setEmail(request.getEmail())
           .setPhone(request.getPhone())
           .build();
    }

    @Test
    public void test() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        this.mockMvc.perform(MockMvcRequestBuilders
            .post("/api/client")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request))
        ).andExpect(status().isOk())
         .andExpect(content().contentType(MediaType.APPLICATION_JSON))
         .andExpect(content().json(mapper.writeValueAsString(response)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"hr@mail,ru", "!#$#@mail.ru", "hr@mail"})
    public void tesInvalidEmail(String invalidEmail) throws Exception {
        request.setEmail(invalidEmail);
        ObjectMapper mapper = new ObjectMapper();
        this.mockMvc.perform(MockMvcRequestBuilders
            .post("/api/client")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request))
        ).andExpect(status().is4xxClientError());
    }

}

