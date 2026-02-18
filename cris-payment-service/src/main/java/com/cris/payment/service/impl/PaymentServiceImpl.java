package com.cris.payment.service.impl;

import com.cris.payment.entity.PaymentTransaction;
import com.cris.payment.repository.PaymentRepository;
import com.cris.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repository;

    @Override
    public PaymentTransaction initiatePayment(PaymentTransaction transaction) {
        transaction.setTransactionId("TXN-" + UUID.randomUUID().toString().toUpperCase());
        // Mock successful payment logic
        transaction.setStatus("SUCCESS");
        transaction.prePersist();
        return repository.save(transaction);
    }

    @Override
    public PaymentTransaction getTransactionById(String transactionId) {
        return repository.findByTransactionId(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    @Override
    public List<PaymentTransaction> getTransactionsByCitizen(String citizenId) {
        return repository.findByCitizenId(citizenId);
    }
}
