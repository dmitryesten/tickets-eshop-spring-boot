package com.denisenko.airlineticketsshop.config;

import com.denisenko.airlineticketsshop.model.entity.User;
import com.denisenko.airlineticketsshop.model.hibernate.CookieHibernateImpl;
import com.denisenko.airlineticketsshop.model.hibernate.LoginHibernateImpl;
import com.denisenko.airlineticketsshop.model.jdbc.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;


@Configuration
@Import(DataSourceConfig.class)
public class AppConfiguration {

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

    @Bean
    @Primary
    @Qualifier("loginJdbcImpl")
    public ILoginDao<User> getLogin(){
        return new LoginJdbcImpl();
    }

    @Bean
    @Primary
    @Qualifier("loginHibernateImpl")
    public LoginHibernateImpl getLoginHibernate(){return new LoginHibernateImpl();}

    @Bean
    @Primary
    @Qualifier("cookieJdbc")
    public ICookieDao getCookie(){
        return new CookieJdbcImpl();
    }

    @Bean
    @Qualifier("cookieHibernateImpl")
    public ICookieDao getCookieHibernate(){
        return new CookieHibernateImpl();
    }

    @Bean
    @Primary
    @Qualifier("userJdbc")
    public IUserDao getUserJdbc(){
        return new UserJdbcImpl();
    }

}
