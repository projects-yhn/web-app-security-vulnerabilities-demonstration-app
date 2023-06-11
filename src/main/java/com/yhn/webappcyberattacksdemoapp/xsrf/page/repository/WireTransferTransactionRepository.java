package com.yhn.webappcyberattacksdemoapp.xsrf.page.repository;

import com.yhn.webappcyberattacksdemoapp.xsrf.page.model.entity.WireTransferTransaction;
import com.yhn.webappcyberattacksdemoapp.xsrf.page.model.enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WireTransferTransactionRepository extends JpaRepository<WireTransferTransaction, Long> {
    Optional<WireTransferTransaction> findFirstByTransactionStatusOrderByCreationTsAsc(TransactionStatus status);
}
