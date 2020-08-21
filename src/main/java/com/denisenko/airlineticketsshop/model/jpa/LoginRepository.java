package com.denisenko.airlineticketsshop.model.jpa;

import com.denisenko.airlineticketsshop.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long> {
}
