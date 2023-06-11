package com.yhn.webappcyberattacksdemoapp.xsrf.page.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WireTransferRequestModel {
    private String accountType;
    private String receiver;
    private String receiverIBAN;
    private String receiverAccountCurrency;
    private BigDecimal amount;
    private String amountCurrency;
    private String description;
}
