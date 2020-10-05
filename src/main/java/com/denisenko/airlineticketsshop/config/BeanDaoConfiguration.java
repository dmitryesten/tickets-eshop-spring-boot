package com.denisenko.airlineticketsshop.config;

import com.denisenko.airlineticketsshop.model.hibernate.CookieHibernateImpl;
import com.denisenko.airlineticketsshop.model.jdbc.CookieJdbcImpl;
import com.denisenko.airlineticketsshop.model.jdbc.ICookieDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableAutoConfiguration
public class BeanDaoConfiguration {

    @Bean
    @Qualifier("cookieJdbc")
    public ICookieDao getCookie(){
        return new CookieJdbcImpl();
    }

    @Bean
    @Primary
    @Qualifier("cookieHibernateImpl")
    public ICookieDao getCookieHibernate(){
        return new CookieHibernateImpl();
    }

}
