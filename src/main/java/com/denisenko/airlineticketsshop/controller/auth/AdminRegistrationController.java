package com.denisenko.airlineticketsshop.controller.auth;

import com.denisenko.airlineticketsshop.model.entity.Login;
import com.denisenko.airlineticketsshop.model.entity.Administrator;
import com.denisenko.airlineticketsshop.model.entity.UserType;
import com.denisenko.airlineticketsshop.model.jdbc.UserCreateJdbcImpl;
import com.denisenko.airlineticketsshop.model.dto.request.AdminRegistrationRequest;
import com.denisenko.airlineticketsshop.model.dto.response.AdminRegistrationResponse;
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
    UserCreateJdbcImpl adminCreateJdbc;

    @PostMapping(value = "/api/admin")
    public ResponseEntity<AdminRegistrationResponse> createAdmin(@Valid @RequestBody AdminRegistrationRequest request) throws SQLException {
        Administrator user = new Administrator();
        user.setLoginObject(new Login(request.getLogin(), request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPatronymicName(request.getPatronymic());
        user.setUserType(UserType.ADMIN);
        user.setPosition(request.getPosition());

        Administrator createdUser = (Administrator) adminCreateJdbc.create(user);

        return ResponseEntity.ok()
            .body(AdminRegistrationResponse.builder()
                    .setId(createdUser.getId())
                    .setFirstName(createdUser.getFirstName())
                    .setLastName(createdUser.getLastName())
                    .setPatronymicName(createdUser.getPatronymicName())
                    .setPosition(createdUser.getPosition())
                    .setUserType(UserType.ADMIN)
                    .build()
            );
    }

}
