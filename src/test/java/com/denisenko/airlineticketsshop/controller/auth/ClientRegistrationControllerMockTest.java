package com.denisenko.airlineticketsshop.controller.auth;

import com.denisenko.airlineticketsshop.config.AppConfiguration;
import com.denisenko.airlineticketsshop.model.dto.request.ClientRegistrationRequest;
import com.denisenko.airlineticketsshop.model.dto.response.ClientRegistrationResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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
       request.setLogin("W"+UUID.randomUUID().toString());
       request.setPassword("123");
       request.setPhone("7");
       request.setEmail("i@mail.ru");
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
         .andExpect(header().exists("Set-Cookie"))
         .andExpect(jsonPath("$.id").isNotEmpty())
         .andExpect(jsonPath("$.userType").value("CLIENT"));

    }

    @ParameterizedTest
    @ValueSource(strings = {"hr@mail,ru", "!#$#@mail.ru", "hr@mail"})
    public void tesInvalidEmail(String invalidEmail) throws Exception {
        request.setEmail(invalidEmail);
        ObjectMapper mapper = new ObjectMapper();
        request.setLogin("Login"+ UUID.randomUUID().toString());
        this.mockMvc.perform(MockMvcRequestBuilders
            .post("/api/client")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request))
        ).andExpect(status().is4xxClientError());
    }

}

