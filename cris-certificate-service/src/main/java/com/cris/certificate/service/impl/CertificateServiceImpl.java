package com.cris.certificate.service.impl;

import com.cris.certificate.entity.CertificateRequest;
import com.cris.certificate.repository.CertificateRepository;
import com.cris.certificate.service.CertificateService;
import com.cris.certificate.service.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository repository;
    private final PdfService pdfService;

    @Override
    public CertificateRequest createRequest(CertificateRequest request) {
        request.prePersist();
        return repository.save(request);
    }

    @Override
    public List<CertificateRequest> getRequestsByCitizen(String citizenId) {
        return repository.findByCitizenId(citizenId);
    }

    @Override
    public CertificateRequest updateStatus(String id, String status) {
        CertificateRequest request = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus(status);
        return repository.save(request);
    }

    @Override
    public List<CertificateRequest> getRequestsByStatus(String status) {
        return repository.findByStatus(status);
    }

    @Override
    public CertificateRequest getRequestById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
    }

    @Override
    public byte[] generateCertificatePdf(String id) {
        CertificateRequest request = getRequestById(id);
        return pdfService.generateCertificatePdf(request);
    }
}
