package com.yhn.webappcyberattacksdemoapp.xsrf.page;

import com.yhn.webappcyberattacksdemoapp.xsrf.page.model.entity.BankUser;
import com.yhn.webappcyberattacksdemoapp.xsrf.page.model.entity.BankUserAccount;
import com.yhn.webappcyberattacksdemoapp.xsrf.page.model.entity.WireTransferTransaction;
import com.yhn.webappcyberattacksdemoapp.xsrf.page.model.enums.TransactionStatus;
import com.yhn.webappcyberattacksdemoapp.xsrf.page.repository.BankUserAccountRepository;
import com.yhn.webappcyberattacksdemoapp.xsrf.page.repository.WireTransferTransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WireTransferTransactionManager {

    private final WireTransferTransactionRepository wireTransferTransactionRepository;
    private final BankUserAccountRepository bankUserAccountRepository;

    @Scheduled(fixedDelay = 10_000)
    @Transactional
    public void executeWireTransferTransaction() {
        Optional<WireTransferTransaction> transactionOptional
                = wireTransferTransactionRepository.findFirstByTransactionStatusOrderByCreationTsAsc(TransactionStatus.PENDING);
        if (transactionOptional.isEmpty()) {
            return;
        }
        WireTransferTransaction transaction = transactionOptional.get();

        BigDecimal desiredAmount = transaction.getAmount();
        BankUserAccount senderAccount = transaction.getAccount();

        if (senderAccount.getAmount().compareTo(desiredAmount) < 0) {
            log.warn("Can not complete transaction successfully. There is no enough money.");
            transaction.setTransactionStatus(TransactionStatus.REJECTED);
            wireTransferTransactionRepository.save(transaction);
            return;
        } else {
            log.info("Removing from sender");
            BigDecimal transactionAmount = transaction.getAmount();
            senderAccount.setAmount(senderAccount.getAmount().subtract(transactionAmount));
            bankUserAccountRepository.save(senderAccount);

            Optional<BankUserAccount> receiverAccountFromCurrentBankService
                    = bankUserAccountRepository.getFirstByIban(transaction.getReceiverIban());
            if (receiverAccountFromCurrentBankService.isPresent()) {
                log.info("Receiver for transaction with id - {} is customer", transaction.getTransactionId());
                log.info("Adding money to receiver");
                BankUserAccount receiverAccount = receiverAccountFromCurrentBankService.get();
                BigDecimal receiverAmount = receiverAccount.getAmount();
                receiverAccount.setAmount(receiverAmount.add(transactionAmount));
                bankUserAccountRepository.save(receiverAccount);
            } else {
                log.info("Sending money to receiver.");
                // TODO make payment to other bank
            }
            transaction.setTransactionStatus(TransactionStatus.APPROVED);
            wireTransferTransactionRepository.save(transaction);
        }
    }
}
