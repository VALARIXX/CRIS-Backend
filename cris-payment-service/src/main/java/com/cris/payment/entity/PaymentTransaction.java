package com.cris.payment.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "payment_transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentTransaction {

    @Id
    private String id;

    @Indexed(unique = true)
    private String transactionId;

    private String citizenId;

    private String requestId; // Linked to Certificate Request ID

    private Double amount;

    private String status; // SUCCESS, FAILED, PENDING

    private String paymentMethod;

    private LocalDateTime transactionDate;

    public void prePersist() {
        this.transactionDate = LocalDateTime.now();
        if (this.status == null) {
            this.status = "PENDING";
        }
    }
}
