package com.cris.payment.controller;

import com.cris.payment.entity.PaymentTransaction;
import com.cris.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @PostMapping("/initiate")
    public ResponseEntity<PaymentTransaction> initiatePayment(@RequestBody PaymentTransaction transaction) {
        return ResponseEntity.ok(service.initiatePayment(transaction));
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<PaymentTransaction> getTransaction(@PathVariable String transactionId) {
        return ResponseEntity.ok(service.getTransactionById(transactionId));
    }

    @GetMapping("/citizen/{citizenId}")
    public ResponseEntity<List<PaymentTransaction>> getTransactionsByCitizen(@PathVariable String citizenId) {
        return ResponseEntity.ok(service.getTransactionsByCitizen(citizenId));
    }
}
