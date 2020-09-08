package com.denisenko.airlineticketsshop.controller.auth;

import com.denisenko.airlineticketsshop.controller.factory.CookieFactory;
import com.denisenko.airlineticketsshop.controller.factory.HttpHeaderFactory;
import com.denisenko.airlineticketsshop.model.entity.*;
import com.denisenko.airlineticketsshop.model.jdbc.ICookieDao;
import com.denisenko.airlineticketsshop.model.jdbc.ILoginDao;
import com.denisenko.airlineticketsshop.model.jdbc.IUserDao;
import com.denisenko.airlineticketsshop.model.dto.request.AdminRegistrationRequest;
import com.denisenko.airlineticketsshop.model.dto.response.AdminRegistrationResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.UUID;

@RestController
public class AdminRegistrationController {

    @Autowired
    @Qualifier("userJdbc")
    private IUserDao<Administrator> adminDao;

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

        Administrator createdUser = adminDao.create(user);

        Cookie cookie = CookieFactory.getCookie();
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

}
