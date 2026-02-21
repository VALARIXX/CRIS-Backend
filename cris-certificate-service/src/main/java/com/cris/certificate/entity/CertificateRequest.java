package com.cris.certificate.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "certificate_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CertificateRequest {

    @Id
    private String id;

    @Indexed(unique = true)
    private String requestId; // Unique request identifier for tracking

    private String citizenId;

    private String type; // Birth, Death, Income, etc.

    private String status; // PENDING, APPROVED, REJECTED

    private LocalDateTime applicationDate;

    private String comments;

    private String documentUrl;

    private String requestDetails;

    private Double amount;

    private LocalDateTime processedAt; // Set when status changes to APPROVED/REJECTED

    public void prePersist() {
        this.applicationDate = LocalDateTime.now();
        if (this.status == null) {
            this.status = "PENDING";
        }
        if (this.requestId == null) {
            this.requestId = "REQ" + System.currentTimeMillis();
        }
    }
}
