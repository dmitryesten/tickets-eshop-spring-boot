package com.denisenko.airlineticketsshop;

import com.denisenko.airlineticketsshop.model.Login;
import com.denisenko.airlineticketsshop.model.jpa.LoginRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AirlineTicketsShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlineTicketsShopApplication.class, args);
	}

	@Bean
	InitializingBean saveData(LoginRepository repo){
		return () -> {
			repo.save(new Login(1L, "Dima", "123"));
			repo.save(new Login(2L, "Airline", "134"));
			repo.save(new Login(3L, "Dule", "312"));
		};
	}

}
