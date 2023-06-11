package com.yhn.webappcyberattacksdemoapp.xsrf.page.repository;

import com.yhn.webappcyberattacksdemoapp.xsrf.page.model.entity.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankUserRepository extends JpaRepository<BankUser, Long> {
    Optional<BankUser> findFirstByUsername(String username);
}
