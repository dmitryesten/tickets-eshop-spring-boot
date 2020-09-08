package com.denisenko.airlineticketsshop.controller.auth;

import com.denisenko.airlineticketsshop.controller.factory.CookieFactory;
import com.denisenko.airlineticketsshop.controller.factory.HttpHeaderFactory;
import com.denisenko.airlineticketsshop.model.entity.CookieLogin;
import com.denisenko.airlineticketsshop.model.entity.Login;
import com.denisenko.airlineticketsshop.model.entity.Client;
import com.denisenko.airlineticketsshop.model.entity.UserType;
import com.denisenko.airlineticketsshop.model.jdbc.ICookieDao;
import com.denisenko.airlineticketsshop.model.jdbc.IUserDao;
import com.denisenko.airlineticketsshop.model.dto.request.ClientRegistrationRequest;
import com.denisenko.airlineticketsshop.model.dto.response.ClientRegistrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.UUID;

@RestController
public class ClientRegistrationController {

    @Autowired
    @Qualifier("userJdbc")
    private IUserDao<Client> clientDao;

    @Autowired
    private ICookieDao cookieDao;

    @PostMapping(value = "/api/client")
    public ResponseEntity<ClientRegistrationResponse> createClient(@Valid @RequestBody ClientRegistrationRequest request) throws SQLException {
        Client user = new Client.ClientBuilder()
            .setFirstName(request.getFirstName())
            .setLastName(request.getLastName())
            .setPatronymicName(request.getPatronymic())
            .setLogin(new Login(request.getLogin(), request.getPassword()))
            .setEmail(request.getEmail())
            .setPhone(request.getPhone())
            .setUserType(UserType.CLIENT)
            .build();

        Client createdUser = clientDao.create(user);

        Cookie cookie = CookieFactory.getCookie();
        CookieLogin cookieLogin = cookieDao.create(createdUser.getLoginObject(), cookie);

        return ResponseEntity.ok()
            .headers(HttpHeaderFactory.getHttpHeader(cookieLogin))
            .body(ClientRegistrationResponse.builder()
                .setId(createdUser.getId())
                .setFirstName(createdUser.getFirstName())
                .setLastName(createdUser.getLastName())
                .setPatronymicName(createdUser.getPatronymicName())
                .setUserType(user.getUserType())
                .setEmail(user.getEmail())
                .setPhone(user.getPhone())
                .build());
    }

}
