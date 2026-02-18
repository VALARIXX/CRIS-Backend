package com.cris.payment.repository;

import com.cris.payment.entity.PaymentTransaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends MongoRepository<PaymentTransaction, String> {
    Optional<PaymentTransaction> findByTransactionId(String transactionId);

    List<PaymentTransaction> findByCitizenId(String citizenId);
}
