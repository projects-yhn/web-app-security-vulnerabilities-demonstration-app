package com.yhn.webappcyberattacksdemoapp.xsrf.page.model.dto;

import com.yhn.webappcyberattacksdemoapp.xsrf.page.model.enums.AccountType;
import com.yhn.webappcyberattacksdemoapp.xsrf.page.model.enums.Currency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankUserAccountDto {
    private Long id;
    private AccountType type;
    private Currency currency;
    private BigDecimal amount;
    private String iban;
    private BankUserDto bankUser;
}
