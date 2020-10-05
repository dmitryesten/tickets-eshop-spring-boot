package com.denisenko.airlineticketsshop.controller.account;

import com.denisenko.airlineticketsshop.controller.factory.CookieFactory;
import com.denisenko.airlineticketsshop.controller.factory.HttpHeaderFactory;
import com.denisenko.airlineticketsshop.model.dto.response.AbstractUserResponseDto;
import com.denisenko.airlineticketsshop.model.dto.response.factory.UserFactoryResponseDto;
import com.denisenko.airlineticketsshop.model.entity.CookieLogin;
import com.denisenko.airlineticketsshop.model.entity.AppCookie;
import com.denisenko.airlineticketsshop.model.entity.User;
import com.denisenko.airlineticketsshop.model.jdbc.IUserDao;
import com.denisenko.airlineticketsshop.service.exception.CookieNotFoundException;
import com.denisenko.airlineticketsshop.service.exception.InvalidObjectFactoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;

@RestController
@RequestMapping(path = "/api/account")
public class AccountController {

    @Autowired
    private IUserDao userDao;;

    @GetMapping
    public ResponseEntity<AbstractUserResponseDto> getAccount(@RequestHeader @CookieValue(name="JAVASESSIONID") String cookie) throws InvalidObjectFactoryException {
        if(cookie.isEmpty())
            throw new CookieNotFoundException("The key JAVASESSIONID of cookie mustn't be empty");

        Cookie currentCookie = CookieFactory.getCookie(cookie);

        User user = userDao.select(currentCookie);

        return ResponseEntity.ok()
            .headers(HttpHeaderFactory.getHttpHeader(new CookieLogin((AppCookie) currentCookie)))
            .body(UserFactoryResponseDto.getUserResponseDto(user));
    }

}
