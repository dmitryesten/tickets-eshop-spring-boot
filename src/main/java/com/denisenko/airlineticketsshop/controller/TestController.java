package com.denisenko.airlineticketsshop.controller;

import com.denisenko.airlineticketsshop.model.Login;
import com.denisenko.airlineticketsshop.model.jpa.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
public class TestController {
    @Autowired
    LoginRepository loginRepository;

    @RequestMapping(value = "/login")
    public @ResponseBody
    List<Login> getAllLogin() {
        return loginRepository.findAll();
    }

    @GetMapping("/")
    public String readCookie(@CookieValue(value = "JAVASESSIONID", defaultValue = "123") String cookie) {
        return "Hey! My username is " + cookie;
    }

}
