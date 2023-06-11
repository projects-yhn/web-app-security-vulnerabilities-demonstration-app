package com.yhn.webappcyberattacksdemoapp.xsrf.page;

import com.yhn.webappcyberattacksdemoapp.xsrf.page.model.entity.BankUser;
import com.yhn.webappcyberattacksdemoapp.xsrf.page.model.entity.BankUserAccount;
import com.yhn.webappcyberattacksdemoapp.xsrf.page.model.entity.WireTransferTransaction;
import com.yhn.webappcyberattacksdemoapp.xsrf.page.model.enums.AccountType;
import com.yhn.webappcyberattacksdemoapp.xsrf.page.model.enums.Currency;
import com.yhn.webappcyberattacksdemoapp.xsrf.page.model.enums.TransactionStatus;
import com.yhn.webappcyberattacksdemoapp.xsrf.page.model.request.WireTransferRequestModel;
import com.yhn.webappcyberattacksdemoapp.xsrf.page.repository.BankUserAccountRepository;
import com.yhn.webappcyberattacksdemoapp.xsrf.page.repository.BankUserRepository;
import com.yhn.webappcyberattacksdemoapp.xsrf.page.repository.WireTransferTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OnlineBankingService {

    private final WireTransferTransactionRepository wireTransferRepository;
    private final BankUserRepository bankUserRepository;
    private final BankUserAccountRepository bankUserAccountRepository;

    public void makeMoneyWireTransferTransaction(WireTransferRequestModel model) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        BankUser bankUser = bankUserRepository.findFirstByUsername(username)
                .orElseThrow();
        BankUserAccount bankUserAccount = bankUserAccountRepository.getByBankUser(bankUser)
                .orElseThrow();
        WireTransferTransaction entity = new WireTransferTransaction();
        entity.setTransactionId(UUID.randomUUID().toString());
        entity.setBankUser(bankUser);
        entity.setAccount(bankUserAccount);
        entity.setAmount(model.getAmount());
        entity.setAmountCurrency(Currency.valueOf(model.getAmountCurrency()));
        entity.setReceiverNames(model.getReceiver());
        entity.setReceiverIban(model.getReceiverIBAN());
        entity.setDescription(model.getDescription());
        entity.setTransactionStatus(TransactionStatus.PENDING);
        entity.setCreationTs(Instant.now());

        wireTransferRepository.save(entity);

    }
}
