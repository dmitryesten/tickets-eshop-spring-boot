package com.denisenko.airlineticketsshop.controller.auth;

import com.denisenko.airlineticketsshop.controller.factory.HttpHeaderFactory;
import com.denisenko.airlineticketsshop.model.dto.request.LoginRequestDto;
import com.denisenko.airlineticketsshop.model.entity.*;
import com.denisenko.airlineticketsshop.model.jdbc.ICookieDao;
import com.denisenko.airlineticketsshop.model.jdbc.ILoginDao;
import com.denisenko.airlineticketsshop.model.jdbc.IUserCreateDao;
import com.denisenko.airlineticketsshop.model.jdbc.UserCreateJdbcImpl;
import com.denisenko.airlineticketsshop.model.dto.request.AdminRegistrationRequest;
import com.denisenko.airlineticketsshop.model.dto.response.AdminRegistrationResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.crypto.spec.OAEPParameterSpec;
import javax.servlet.http.Cookie;
import javax.validation.Valid;
import java.net.URI;
import java.sql.SQLException;
import java.util.UUID;

@RestController
public class AdminRegistrationController {

    @Autowired
    @Qualifier("userCreateJdbc")
    private IUserCreateDao<Administrator> adminCreateJdbc;

    @Autowired
    @Qualifier("loginJdbcImpl")
    private ILoginDao<User> loginDao;

    @Autowired
    private ICookieDao cookieDao;


    @PostMapping(value = "/api/admin")
    public ResponseEntity<AdminRegistrationResponse> createAdmin(@Valid @RequestBody AdminRegistrationRequest request) throws SQLException, JsonProcessingException {
        Administrator user = new Administrator();
        user.setLoginObject(new Login(request.getLogin(), request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPatronymicName(request.getPatronymic());
        user.setUserType(UserType.ADMIN);
        user.setPosition(request.getPosition());

        Administrator createdUser = adminCreateJdbc.create(user);

        Cookie cookie = new Cookie("JAVASESSIONID", UUID.randomUUID().toString());
        cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
        cookie.setPath("/");
        cookie.setSecure(false);
        cookie.setHttpOnly(false);
        CookieLogin cookieLogin = cookieDao.create(createdUser.getLoginObject(), cookie);

        return ResponseEntity.ok()
            .headers(HttpHeaderFactory.getHttpHeader(cookieLogin))
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
    @PostMapping("/redirect")
    public ResponseEntity<String> getRedirect(){
        return ResponseEntity.ok().body("{}");
    }

}
