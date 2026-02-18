package com.cris.certificate.controller;

import com.cris.certificate.entity.CertificateRequest;
import com.cris.certificate.service.CertificateService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/certificates")
@RequiredArgsConstructor
public class CertificateController {
    @Autowired
    private CertificateService service;

    @PostMapping("/request")
    public ResponseEntity<CertificateRequest> createRequest(@RequestBody CertificateRequest request) {
        return ResponseEntity.ok(service.createRequest(request));
    }

    @GetMapping("/citizen/{citizenId}")
    public ResponseEntity<List<CertificateRequest>> getRequestsByCitizen(@PathVariable String citizenId) {
        return ResponseEntity.ok(service.getRequestsByCitizen(citizenId));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<CertificateRequest> updateStatus(@PathVariable String id,
            @RequestBody Map<String, String> body) {
        String status = body.get("status");
        return ResponseEntity.ok(service.updateStatus(id, status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificateRequest> getRequestById(@PathVariable String id) {
        return ResponseEntity.ok(service.getRequestById(id));
    }

    @GetMapping("/pending")
    public ResponseEntity<List<CertificateRequest>> getPendingRequests() {
        return ResponseEntity.ok(service.getRequestsByStatus("PENDING"));
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadCertificate(@PathVariable String id) {
        byte[] pdfBytes = service.generateCertificatePdf(id);

        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .header("Content-Disposition", "attachment; filename=certificate_" + id + ".pdf")
                .body(pdfBytes);
    }
}
