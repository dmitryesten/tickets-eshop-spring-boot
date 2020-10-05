package com.denisenko.airlineticketsshop.model.hibernate;

import com.denisenko.airlineticketsshop.config.AppConfiguration;
import com.denisenko.airlineticketsshop.controller.factory.CookieFactory;
import com.denisenko.airlineticketsshop.model.entity.CookieLogin;
import com.denisenko.airlineticketsshop.model.entity.Login;
import com.denisenko.airlineticketsshop.model.jdbc.ICookieDao;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.servlet.http.Cookie;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({AppConfiguration.class})
@DataJpaTest
class CookieHibernateImplTest {

    @Autowired
    private ICookieDao cookieHibernate;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void create() {
        Login login = new Login(1L, "Test1", "123");
        CookieLogin cookieLogin = cookieHibernate.create(login, CookieFactory.getCookie());
        Assert.assertNotEquals(0, cookieLogin.getId());
        Assert.assertNotEquals(-1, cookieLogin.getId());
        System.out.println("CookieLogin coolie = " + cookieLogin.getCookie().toString());
    }

    @Test
    void testGetByLogin(){

        Login login = new Login(1L, "Test12", "123");
        //cookieHibernate.create(login, CookieFactory.getCookie());
        List<CookieLogin> cookieLogin = cookieHibernate.getCookieByLoginId(login);
        Assert.assertNotNull(cookieLogin);
        System.out.println(cookieLogin);
        cookieLogin.stream().forEach(s -> System.out.println("Cookie = "+ s.getCookie() ));

    }

    @Test
    void testGet() {
        CookieLogin cookieLogin = sessionFactory.openSession().get(CookieLogin.class, 101L);
    }

}