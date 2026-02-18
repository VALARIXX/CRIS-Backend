package com.cris.citizen.controller;

import com.cris.citizen.entity.*;
import com.cris.citizen.service.CitizenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/citizens")
@RequiredArgsConstructor
public class CitizenController {

    private final CitizenService service;

    @PostMapping("/enroll")
    public Citizen enroll(@RequestBody Citizen c) {
        return service.registerCitizen(c);
    }

    @GetMapping("/{civicId}")
    public Citizen get(@PathVariable String civicId) {
        return service.findCitizenByCivicId(civicId);
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
