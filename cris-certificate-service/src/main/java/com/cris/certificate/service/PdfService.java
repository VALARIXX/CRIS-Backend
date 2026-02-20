package com.cris.certificate.service;

import com.cris.certificate.entity.CertificateRequest;

public interface PdfService {
    byte[] generateCertificatePdf(CertificateRequest request);
}
