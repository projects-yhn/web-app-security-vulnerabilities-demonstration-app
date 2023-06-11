package com.yhn.webappcyberattacksdemoapp.xsrf.page.model.entity;

import com.yhn.webappcyberattacksdemoapp.xsrf.page.model.enums.Currency;
import com.yhn.webappcyberattacksdemoapp.xsrf.page.model.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "xsrf_wire_transfer_transactions")
public class WireTransferTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String transactionId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id")
    private BankUser bankUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_account_id")
    private BankUserAccount account;

    @Column
    private BigDecimal amount;

    @Column
    @Enumerated(EnumType.STRING)
    private Currency amountCurrency;

    @Column
    private String receiverNames;

    @Column
    private String receiverIban;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Column
    private Instant creationTs;
}
