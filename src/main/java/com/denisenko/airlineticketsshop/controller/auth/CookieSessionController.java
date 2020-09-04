package com.denisenko.airlineticketsshop.controller.auth;

import com.denisenko.airlineticketsshop.controller.factory.HttpHeaderFactory;
import com.denisenko.airlineticketsshop.model.dto.response.AbstractUserRegistrationResponseDto;
import com.denisenko.airlineticketsshop.model.dto.response.factory.UserFactoryResponseDto;
import com.denisenko.airlineticketsshop.model.entity.*;
import com.denisenko.airlineticketsshop.model.dto.request.LoginRequestDto;
import com.denisenko.airlineticketsshop.service.CookieSessionService;
import com.denisenko.airlineticketsshop.service.LoginService;
import com.denisenko.airlineticketsshop.service.exception.CookieNotFoundException;
import com.denisenko.airlineticketsshop.service.exception.InvalidObjectFactoryException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


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
    public ResponseEntity<AbstractUserRegistrationResponseDto> getUserDataByLogin (@Valid @RequestBody LoginRequestDto request) throws SQLException, InvalidObjectFactoryException {

        Login login = modelMapper.map(request, Login.class);
        User user = loginService.getUser(login);

        return ResponseEntity.ok()
            //.headers(HttpHeaderFactory.getHttpHeader())
            .body(UserFactoryResponseDto.getUserResponseDto(user));
    }
    @GetMapping
    public ResponseEntity<String> getHeader(HttpServletResponse response) throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", "JAVASESSIONID1="+UUID.randomUUID().toString());

        Cookie cookie = new Cookie("JAVASESSIONID2", "Jovan");

        String secure = cookie.getSecure() ?";Secure":"";
        String httpOnly = cookie.isHttpOnly() ?";HttpOnly":"";
        httpHeaders.set("Set-Cookie", cookie.getName() + "="+ cookie.getValue() );
        //+"; Path="+ cookie.getPath() + "; Max-Age="+cookie.getMaxAge()+ secure+httpOnly
        //+"; Path="+ cookie.getPath() + "; Max-Age=" + cookie.getMaxAge()


        return ResponseEntity.ok().headers(httpHeaders).body("Hello");
    }

    @GetMapping(value = "/a")
    public ResponseEntity<String> getHeader(@RequestHeader @CookieValue(name="JAVASESSIONID2", required = false) String cookie) throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        Cookie cookie2 = new Cookie("JAVASESSIONID2", "Jovan");
        cookie2.setMaxAge(0); // expires in 7 days
        cookie2.setPath("/");
        cookie2.setSecure(false);
        cookie2.setHttpOnly(false);
        cookie2.setHttpOnly(true);
        String secure = cookie2.getSecure() ?";Secure":"";
        String httpOnly = cookie2.isHttpOnly() ?";HttpOnly":"";
        httpHeaders.set("Set-Cookie", cookie2.getName() + "="+ cookie2.getValue() +"; Path="+ cookie2.getPath() + "; Max-Age="+cookie2.getMaxAge()+ secure+httpOnly);

        return ResponseEntity.ok().headers(httpHeaders).body(cookie);
    }


    @DeleteMapping
    public ResponseEntity deleteSessionInRepository(@RequestHeader @CookieValue(name="JAVASESSIONID2", required = false) String javaSessionId) throws SQLException {
        /*
        if(javaSessionId.isEmpty())
            throw new CookieNotFoundException("The key JAVASESSIONID of cookie mustn't be empty");
        */

        //cookieSessionService.deleteCookie(javaSessionId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.remove(HttpHeaders.SET_COOKIE);

        return ResponseEntity.ok().headers(httpHeaders).body(" {}"+ javaSessionId);
    }

}
