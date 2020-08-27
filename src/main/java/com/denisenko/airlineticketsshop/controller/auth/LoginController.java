package com.denisenko.airlineticketsshop.controller.auth;

import com.denisenko.airlineticketsshop.model.dto.response.AbstractUserRegistrationResponseDto;
import com.denisenko.airlineticketsshop.model.dto.response.factory.UserFactoryResponseDto;
import com.denisenko.airlineticketsshop.model.entity.*;
import com.denisenko.airlineticketsshop.model.dto.request.LoginRequestDto;
import com.denisenko.airlineticketsshop.model.jdbc.ILoginDao;
import com.denisenko.airlineticketsshop.service.LoginService;
import com.denisenko.airlineticketsshop.service.exception.InvalidObjectFactoryException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.SQLException;


@RestController
public class LoginController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LoginService loginService;
    @Autowired
    private ILoginDao<? extends User> loginDao;

    @PostMapping(value = "/api/session")
    public ResponseEntity<AbstractUserRegistrationResponseDto> getUserDataByLogin(@Valid @RequestBody LoginRequestDto request) throws SQLException, InvalidObjectFactoryException {
        Login login = modelMapper.map(request, Login.class);
        User user = loginService.getUser(login);
        return ResponseEntity.ok()
            .body(UserFactoryResponseDto.getUserResponseDto(user));
    }

}
