package com.denisenko.airlineticketsshop.controller.account;

import com.denisenko.airlineticketsshop.config.AppConfiguration;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@Import(AppConfiguration.class)
class AccountControllerTest {


    private String browserKeyField = "Cookie";
    private final String cookieNameValueAdmin = "JAVASESSIONID=13";
    private final String cookieNameValueClient = "JAVASESSIONID=14";

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @ValueSource(strings = {cookieNameValueAdmin, cookieNameValueClient})
    void getAccountAdmin(String cookieNameValue) throws Exception {

        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/api/account")
                .contentType(MediaType.APPLICATION_JSON)
                .header(browserKeyField, cookieNameValue)
        ).andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(header().exists("Set-Cookie"))
            .andExpect(jsonPath("$.id").isNotEmpty())
            .andExpect(jsonPath("$.userType").isNotEmpty());
    }

}