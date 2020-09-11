package com.denisenko.airlineticketsshop.model.hibernate;

import com.denisenko.airlineticketsshop.model.entity.Login;
import com.denisenko.airlineticketsshop.model.entity.User;
import com.denisenko.airlineticketsshop.model.jdbc.IUserDao;
import javax.servlet.http.Cookie;
import java.sql.SQLException;


public class UserHibernateImpl implements IUserDao {

    @Override
    public User create(User user) throws SQLException {
        return null;
    }

    @Override
    public User select(Cookie cookie) {
        return null;
    }

    @Override
    public User select(Login login) {
        return null;
    }

}
