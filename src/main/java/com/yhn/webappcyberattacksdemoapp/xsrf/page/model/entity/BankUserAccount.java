package com.yhn.webappcyberattacksdemoapp.xsrf.page.model.entity;

import com.yhn.webappcyberattacksdemoapp.xsrf.page.model.enums.AccountType;
import com.yhn.webappcyberattacksdemoapp.xsrf.page.model.enums.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "xsrf_bank_user_accounts")
public class BankUserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Column
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column
    private BigDecimal amount;

    @Column
    private String iban;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private BankUser bankUser;

}
