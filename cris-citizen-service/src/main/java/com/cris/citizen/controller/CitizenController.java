package com.cris.citizen.controller;

import com.cris.citizen.entity.*;
import com.cris.citizen.service.CitizenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/citizens")
@RequiredArgsConstructor
public class CitizenController {

    private final CitizenService service;

    @GetMapping
    public List<Citizen> getAll() {
        return service.getAllCitizens();
    }

    @PostMapping("/enroll")
    public Citizen enroll(@RequestBody Citizen c) {
        return service.registerCitizen(c);
    }

    @GetMapping("/{civicId}")
    public ResponseEntity<?> get(@PathVariable String civicId) {
        try {
            return ResponseEntity.ok(service.findCitizenByCivicId(civicId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/aadhar/{aadhar}")
    public ResponseEntity<?> getByAadhar(@PathVariable String aadhar) {
        try {
            return ResponseEntity.ok(service.findCitizenByAadhar(aadhar));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/search")
    public List<Citizen> search(@RequestParam String query) {
        return service.searchCitizens(query);
    }

    @PostMapping("/household")
    public Household household(@RequestBody Map<String, Object> payload) {
        return service.createHousehold(
                (String) payload.get("headCitizenId"),
                (Map<String, String>) payload.get("relations"));
    }

    @GetMapping("/household/{id}")
    public List<Citizen> family(@PathVariable String id) {
        return service.findFamilyMembers(id);
    }

    @PutMapping("/{civicId}")
    public Citizen update(@PathVariable String civicId, @RequestBody Citizen c) {
        return service.updateCitizen(civicId, c);
    }
}
