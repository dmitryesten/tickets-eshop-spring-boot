package com.denisenko.airlineticketsshop.model.hibernate;

import com.denisenko.airlineticketsshop.config.AppConfiguration;
import com.denisenko.airlineticketsshop.model.entity.Login;
import com.denisenko.airlineticketsshop.model.jdbc.ILoginDao;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({AppConfiguration.class})
@DataJpaTest
class LoginHibernateImplTest {

    @Autowired
    private ILoginDao loginHibernateImpl;

    @Test
    void getLogin() {
      // List<Login> actualData =  loginHibernate.getLogin();
       //actualData.stream().forEach(s -> System.out.println(s.getId()+ " " + s.getLogin() + " " + s.getPassword() ));
    }

    @Test
    void testGet(){
        //Login login = loginHibernate.get(1L);
       // Assert.assertEquals("Test1", login.getLogin());
    }

    @Test
    void testCreateLogin(){
        Login login = new Login("UserTest", "123");
        login = loginHibernateImpl.create(login);
        Assert.assertNotEquals(-1L, login.getId());
        System.out.println("ID new login = " + login.getId());
    }

    @Test
    void testExistsTrue(){
        Login noLoginInDB = new Login(7770105, "UserTest", "123");
        Assert.assertFalse(loginHibernateImpl.checkExistLogin(noLoginInDB));
    }

    @Test
    void testExistsFalse(){
        Login existedlogin = new Login(1, "Test1", "123");
        Assert.assertTrue(loginHibernateImpl.checkExistLogin(existedlogin));
    }

}