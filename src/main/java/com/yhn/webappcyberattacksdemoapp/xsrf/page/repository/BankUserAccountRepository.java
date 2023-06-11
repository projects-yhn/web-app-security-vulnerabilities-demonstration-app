package com.yhn.webappcyberattacksdemoapp.xsrf.page.repository;

import com.yhn.webappcyberattacksdemoapp.xsrf.page.model.entity.BankUser;
import com.yhn.webappcyberattacksdemoapp.xsrf.page.model.entity.BankUserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankUserAccountRepository extends JpaRepository<BankUserAccount, Long> {
    Optional<BankUserAccount> getByBankUser(BankUser user);
    Optional<BankUserAccount> getFirstByIban(String iban);
}
