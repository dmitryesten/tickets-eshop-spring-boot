package com.denisenko.airlineticketsshop.controller.auth;

import com.denisenko.airlineticketsshop.model.entity.Login;
import com.denisenko.airlineticketsshop.model.entity.Administrator;
import com.denisenko.airlineticketsshop.model.entity.EntitySystem;
import com.denisenko.airlineticketsshop.model.jdbc.UserCreateJdbc;
import com.denisenko.airlineticketsshop.model.rest.request.AdminRegistrationRequest;
import com.denisenko.airlineticketsshop.model.rest.response.AdminRegistrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.SQLException;

@RestController
public class AdminRegistrationController {

    @Autowired
    UserCreateJdbc adminCreateJdbc;

    @PostMapping(value = "/api/admin")
    public ResponseEntity<AdminRegistrationResponse> createAdmin(@Valid @RequestBody AdminRegistrationRequest request) throws SQLException {
        Administrator user = new Administrator();
        user.setLogin(new Login(request.getLogin(), request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPatronymicName(request.getPatronymic());
        user.setUserType(EntitySystem.ADMIN);
        user.setPosition(request.getPosition());

        Administrator createdUser = (Administrator) adminCreateJdbc.create(user);

        return ResponseEntity.ok()
            .body(new AdminRegistrationResponse(
                createdUser.getId(),
                createdUser.getFirstName(),
                createdUser.getLastName(),
                createdUser.getPatronymicName(),
                createdUser.getPosition(),
                EntitySystem.ADMIN.getTypeEntityString()));
    }

}
