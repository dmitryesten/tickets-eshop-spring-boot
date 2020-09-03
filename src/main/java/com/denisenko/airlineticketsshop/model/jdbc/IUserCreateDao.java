package com.denisenko.airlineticketsshop.model.jdbc;

import com.denisenko.airlineticketsshop.model.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IUserCreateDao<T extends User> extends IPrimaryKey {

    T create(T user) throws SQLException;

}
