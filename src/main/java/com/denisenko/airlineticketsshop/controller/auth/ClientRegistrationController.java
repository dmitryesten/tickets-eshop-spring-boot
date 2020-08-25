package com.denisenko.airlineticketsshop.controller.auth;

import com.denisenko.airlineticketsshop.model.entity.Login;
import com.denisenko.airlineticketsshop.model.entity.Client;
import com.denisenko.airlineticketsshop.model.entity.EntitySystem;
import com.denisenko.airlineticketsshop.model.jdbc.UserCreateJdbc;
import com.denisenko.airlineticketsshop.model.rest.request.ClientRegistrationRequest;
import com.denisenko.airlineticketsshop.model.rest.response.ClientRegistrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.SQLException;

@RestController
public class ClientRegistrationController {

    @Autowired
    UserCreateJdbc clientCreateJdbc;

    @PostMapping(value = "/api/client")
    public ResponseEntity<ClientRegistrationResponse> createAdmin(@Valid @RequestBody ClientRegistrationRequest request) throws SQLException {
        Client user = new Client.ClientBuilder()
            .setFirstName(request.getFirstName())
            .setLastName(request.getLastName())
            .setPatronymicName(request.getPatronymic())
            .setLogin(new Login(request.getLogin(), request.getPassword()))
            .setEmail(request.getEmail())
            .setPhone(request.getPhone())
            .setUserType(EntitySystem.CLIENT)
            .build();

        Client createdUser = (Client) clientCreateJdbc.create(user);

        return ResponseEntity.ok()
            .body(new ClientRegistrationResponse(
                createdUser.getId(),
                createdUser.getFirstName(),
                createdUser.getLastName(),
                createdUser.getPatronymicName(),
                createdUser.getEmail(),
                createdUser.getPhone(),
                EntitySystem.CLIENT.getTypeEntityString()));
    }

}
