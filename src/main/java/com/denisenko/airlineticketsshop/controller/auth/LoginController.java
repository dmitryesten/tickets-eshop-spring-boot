package com.denisenko.airlineticketsshop.controller.auth;

import com.denisenko.airlineticketsshop.model.rest.request.LoginRequest;
import com.denisenko.airlineticketsshop.model.rest.response.ClientRegistrationResponse;
import com.denisenko.airlineticketsshop.model.rest.response.UserAbstractResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.SQLException;

@RestController
public class LoginController {

    @PostMapping(value = "/api/session")
    public ResponseEntity<? extends UserAbstractResponse> createAdmin(@Valid @RequestBody LoginRequest request) throws SQLException {
        return ResponseEntity.ok(new ClientRegistrationResponse(1,"Test", "Test", "TEst", "Email", "phone", "client"));
    }

}
