package com.denisenko.airlineticketsshop.config;

import com.denisenko.airlineticketsshop.model.entity.User;
import com.denisenko.airlineticketsshop.model.hibernate.CookieHibernateImpl;
import com.denisenko.airlineticketsshop.model.hibernate.LoginHibernateImpl;
import com.denisenko.airlineticketsshop.model.jdbc.*;
import com.denisenko.airlineticketsshop.service.CookieSessionService;
import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;


@Configuration
@Import({DataSourceConfig.class, BeanDaoConfiguration.class})
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
    public LoginHibernateImpl getLoginHibernateImpl() {
        return new LoginHibernateImpl();
    }


    @Bean
    @Primary
    @Qualifier("userJdbc")
    public IUserDao getUserJdbc(){
        return new UserJdbcImpl();
    }


    @Bean
    @Primary
    @Qualifier("cookieSessionService")
    public CookieSessionService getCookieService(){
        return new CookieSessionService();
    }


}
