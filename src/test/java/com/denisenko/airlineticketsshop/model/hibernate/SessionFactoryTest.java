package com.denisenko.airlineticketsshop.model.hibernate;

import com.denisenko.airlineticketsshop.config.AppConfiguration;
import com.denisenko.airlineticketsshop.config.DataSourceConfig;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({AppConfiguration.class, DataSourceConfig.class})
@DataJpaTest
public class SessionFactoryTest {

    @Autowired
    @Qualifier("getSessionFactory")
    private SessionFactory sessionFactory;

    @Test
    void test(){
        Assert.assertNotNull(sessionFactory.openSession());
    }

}
