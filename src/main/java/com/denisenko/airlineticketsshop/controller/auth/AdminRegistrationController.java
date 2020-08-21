package com.denisenko.airlineticketsshop.controller.auth;

import com.denisenko.airlineticketsshop.model.entity.EntitySystem;
import com.denisenko.airlineticketsshop.model.rest.request.AdminRegistrationRequest;
import com.denisenko.airlineticketsshop.model.rest.response.AdminRegistrationResponse;
import com.denisenko.airlineticketsshop.service.AdminCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AdminRegistrationController {

    @Autowired
    AdminCreateService adminCreateService;

    @PostMapping(value = "/api/admin")
    public ResponseEntity<AdminRegistrationResponse> createAdmin(@CookieValue(value = "JAVASESSIONID", defaultValue = "Atta") String cookie, @Valid @RequestBody AdminRegistrationRequest adminRequest) {
        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, cookie.toString())
            .body(new AdminRegistrationResponse(999L, adminRequest.getFirstName(), adminRequest.getLastName(), adminRequest.getPatronymic(), "кассир", EntitySystem.ADMIN.getTypeEntityString()));
    }

}
