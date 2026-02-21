package com.cris.payment.service;

import com.cris.payment.entity.PaymentTransaction;

import java.util.List;

public interface PaymentService {
    PaymentTransaction initiatePayment(PaymentTransaction transaction);

    PaymentTransaction getTransactionById(String transactionId);

    List<PaymentTransaction> getTransactionsByCitizen(String citizenId);
}
