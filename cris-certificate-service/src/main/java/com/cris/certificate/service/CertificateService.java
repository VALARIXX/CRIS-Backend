package com.cris.certificate.service;

import com.cris.certificate.entity.CertificateRequest;

import java.util.List;

public interface CertificateService {
    CertificateRequest createRequest(CertificateRequest request);

    List<CertificateRequest> getRequestsByCitizen(String citizenId);

    CertificateRequest updateStatus(String id, String status);

    List<CertificateRequest> getRequestsByStatus(String status);

    List<CertificateRequest> getApprovedToday();

    CertificateRequest getRequestById(String id);

    byte[] generateCertificatePdf(String id);
}
