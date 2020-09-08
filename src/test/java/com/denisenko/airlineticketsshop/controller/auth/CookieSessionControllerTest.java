package com.denisenko.airlineticketsshop.controller.auth;

import com.denisenko.airlineticketsshop.config.AppConfiguration;
import com.denisenko.airlineticketsshop.model.dto.request.LoginRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import(AppConfiguration.class)
class CookieSessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private LoginRequestDto adminRequestTestDto;

    private LoginRequestDto clientRequestTestDto;

    @BeforeEach
    void beforeEachMethodTest(){
        adminRequestTestDto = new LoginRequestDto("Test1", "123");
        clientRequestTestDto = new LoginRequestDto("Test2", "123");
    }

    @Test
    void getUserDataByLoginAdmin() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        this.mockMvc.perform(
            MockMvcRequestBuilders.post("/api/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(adminRequestTestDto))
        ).andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(header().exists("Set-Cookie"))
            .andExpect(jsonPath("$.id").isNotEmpty())
            .andExpect(jsonPath("$.userType").value("ADMIN"));
    }

    @Test
    void deleteSessionInRepository() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        this.mockMvc.perform(
            MockMvcRequestBuilders.post("/api/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(clientRequestTestDto))
        ).andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(header().exists("Set-Cookie"))
            .andExpect(jsonPath("$.id").isNotEmpty())
            .andExpect(jsonPath("$.userType").value("CLIENT"));
    }

}