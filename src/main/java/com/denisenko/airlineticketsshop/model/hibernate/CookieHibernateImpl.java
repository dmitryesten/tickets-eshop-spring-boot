package com.denisenko.airlineticketsshop.model.hibernate;

import com.denisenko.airlineticketsshop.config.DataSourceConfig;
import com.denisenko.airlineticketsshop.model.entity.AppCookie;
import com.denisenko.airlineticketsshop.model.entity.CookieLogin;
import com.denisenko.airlineticketsshop.model.entity.Login;
import com.denisenko.airlineticketsshop.model.jdbc.ICookieDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Qualifier("cookieHibernateImpl")
public class CookieHibernateImpl implements ICookieDao  {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void delete(Cookie cookie) throws SQLException {

    }

    @Override
    public CookieLogin create(Login login, Cookie cookie) {
        Session session = sessionFactory.openSession();
        CookieLogin cookieLogin = new CookieLogin((AppCookie) cookie, login);
        long id = (long) session.save(cookieLogin);
        cookieLogin.setId(id);
        return cookieLogin;
    }



    @Override
    public void update(Login login, Cookie cookie) {

    }

    @Override
    public List<CookieLogin> getCookieByLoginId(Login login) {
        Session session = sessionFactory.openSession();
        return new ArrayList<>();
    }
}
