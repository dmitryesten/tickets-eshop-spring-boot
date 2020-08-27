package com.denisenko.airlineticketsshop.model.jdbc;

import com.denisenko.airlineticketsshop.model.entity.Login;
import com.denisenko.airlineticketsshop.model.entity.User;
import com.denisenko.airlineticketsshop.service.exception.InvalidObjectFactoryException;

import java.sql.SQLException;

public interface ILoginDao<T extends User> {

    T getDataLogin(Login login) throws SQLException, InvalidObjectFactoryException;

}
