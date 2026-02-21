package com.cris.certificate.repository;

import com.cris.certificate.entity.CertificateRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CertificateRepository extends MongoRepository<CertificateRequest, String> {
    List<CertificateRequest> findByCitizenId(String citizenId);

    List<CertificateRequest> findByStatus(String status);

    List<CertificateRequest> findByStatusAndProcessedAtBetween(String status, LocalDateTime from, LocalDateTime to);
}
