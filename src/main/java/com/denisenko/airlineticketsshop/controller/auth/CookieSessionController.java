package com.denisenko.airlineticketsshop.controller.auth;

import com.denisenko.airlineticketsshop.model.dto.response.AbstractUserRegistrationResponseDto;
import com.denisenko.airlineticketsshop.model.dto.response.factory.UserFactoryResponseDto;
import com.denisenko.airlineticketsshop.model.entity.*;
import com.denisenko.airlineticketsshop.model.dto.request.LoginRequestDto;
import com.denisenko.airlineticketsshop.service.CookieSessionService;
import com.denisenko.airlineticketsshop.service.LoginService;
import com.denisenko.airlineticketsshop.service.exception.CookieNotFoundException;
import com.denisenko.airlineticketsshop.service.exception.InvalidObjectFactoryException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.Optional;


@RestController
@RequestMapping(path = "/api/session")
public class CookieSessionController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LoginService loginService;
    @Autowired
    private CookieSessionService cookieSessionService;

    @PostMapping
    public ResponseEntity<AbstractUserRegistrationResponseDto> getUserDataByLogin (
        @Valid @RequestBody LoginRequestDto request) throws SQLException, InvalidObjectFactoryException {

        Login login = modelMapper.map(request, Login.class);
        User user = loginService.getUser(login);
        return ResponseEntity.ok()
            .body(UserFactoryResponseDto.getUserResponseDto(user));
    }

    @DeleteMapping
    public ResponseEntity deleteSessionInRepository(@RequestHeader @CookieValue(name="JAVASESSIONID") String javaSessionId) throws SQLException {
        System.out.println("Cookie " + javaSessionId);
        Optional.ofNullable(javaSessionId)
           .orElseThrow(() -> new CookieNotFoundException("The key JAVASESSIONID of cookie mustn't be null"));
        if(javaSessionId.isEmpty())
            throw new CookieNotFoundException("The key JAVASESSIONID of cookie mustn't be empty");

        cookieSessionService.deleteCookie(javaSessionId);
        return ResponseEntity.ok().body("{}");
    }

}
