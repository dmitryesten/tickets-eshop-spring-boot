package com.denisenko.airlineticketsshop.controller.auth;

import com.denisenko.airlineticketsshop.config.AppConfiguration;
import com.denisenko.airlineticketsshop.model.dto.request.AdminRegistrationRequest;
import com.denisenko.airlineticketsshop.model.dto.response.AdminRegistrationResponse;
import com.denisenko.airlineticketsshop.model.entity.Administrator;
import com.denisenko.airlineticketsshop.model.entity.UserType;
import com.denisenko.airlineticketsshop.model.jdbc.IUserCreateDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.sql.SQLException;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//Test jUnit 5
@SpringBootTest
@AutoConfigureMockMvc
@Import(AppConfiguration.class)
class AdminRegistrationControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserCreateDao<Administrator> adminCreateJdbc;

    private AdminRegistrationRequest requestCreateAdmin;
    private AdminRegistrationResponse registrationResponse;

    @BeforeEach
    public void createRequestBodyObject() throws SQLException {
        requestCreateAdmin = new AdminRegistrationRequest();
        requestCreateAdmin.setFirstName("Ivan");
        requestCreateAdmin.setLastName("Ivanov");
        requestCreateAdmin.setPatronymic("Ivanovich");
        requestCreateAdmin.setPosition("Sale Manager");
        requestCreateAdmin.setLogin("AdminLogin");
        requestCreateAdmin.setPassword("password123");

        registrationResponse = new AdminRegistrationResponse();
        registrationResponse.setId(103L);
        registrationResponse.setFirstName(requestCreateAdmin.getFirstName());
        registrationResponse.setLastName(requestCreateAdmin.getLastName());
        registrationResponse.setPatronymic(requestCreateAdmin.getPatronymic());
        registrationResponse.setPosition(requestCreateAdmin.getPosition());
        registrationResponse.setUserType("ADMIN");
    }

    @Test
    public void integrationTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        this.mockMvc.perform(
            MockMvcRequestBuilders.post("/api/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestCreateAdmin))
        ).andExpect(status().isOk())
         .andExpect(content().contentType(MediaType.APPLICATION_JSON))
         .andExpect(content().json(mapper.writeValueAsString(registrationResponse)));
    }

    @Test
    public void mockTestEmptyFieldException() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        requestCreateAdmin.setFirstName("");
        this.mockMvc.perform(
            MockMvcRequestBuilders.post("/api/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestCreateAdmin))
        ).andExpect(status().is4xxClientError());
    }

    @Test
    public void mockTestNullFieldException() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        requestCreateAdmin.setFirstName(null);
        this.mockMvc.perform(
            MockMvcRequestBuilders.post("/api/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestCreateAdmin))
        ).andExpect(status().is4xxClientError());
    }

}