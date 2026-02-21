package com.cris.certificate.service.impl;

import com.cris.certificate.entity.CertificateRequest;
import com.cris.certificate.service.PdfService;
import org.springframework.stereotype.Service;

@Service
public class PdfServiceImpl implements PdfService {
    @Override
    public byte[] generateCertificatePdf(CertificateRequest request) {
        // Mock PDF generation logic
        return ("PDF Content for " + request.getType()).getBytes();
    }
}
