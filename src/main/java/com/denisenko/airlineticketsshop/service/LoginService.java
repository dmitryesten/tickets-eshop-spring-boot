package com.denisenko.airlineticketsshop.service;

import com.denisenko.airlineticketsshop.model.entity.Login;
import com.denisenko.airlineticketsshop.model.entity.User;
import com.denisenko.airlineticketsshop.model.jdbc.ILoginDao;
import com.denisenko.airlineticketsshop.model.dto.request.LoginRequestDto;
import com.denisenko.airlineticketsshop.service.exception.InvalidObjectFactoryException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class LoginService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    @Qualifier("loginJdbcImpl")
    private ILoginDao<? extends User> loginDao;

    public User getUser(Login login) throws SQLException, InvalidObjectFactoryException {
        return loginDao.getDataLogin(login);
    }

}
