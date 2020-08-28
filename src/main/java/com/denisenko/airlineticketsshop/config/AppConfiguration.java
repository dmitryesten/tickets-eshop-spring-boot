package com.denisenko.airlineticketsshop.config;

import com.denisenko.airlineticketsshop.model.entity.Login;
import com.denisenko.airlineticketsshop.model.entity.User;
import com.denisenko.airlineticketsshop.model.jdbc.CookieJdbcImpl;
import com.denisenko.airlineticketsshop.model.jdbc.ICookieJdbc;
import com.denisenko.airlineticketsshop.model.jdbc.ILoginDao;
import com.denisenko.airlineticketsshop.model.jdbc.LoginJdbcImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class AppConfiguration {

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

    @Bean
    public ILoginDao<User> getLogin(){
        return new LoginJdbcImpl();
    }

    @Bean
    public ICookieJdbc getCookie(){
        return new CookieJdbcImpl();
    }

    @Bean
    public DataSourceConfig getDataSourceCongif(){
        return new DataSourceConfig();
    }
}
