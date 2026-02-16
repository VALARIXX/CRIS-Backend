package com.cris.citizen.service.impl;

import com.cris.citizen.entity.Citizen;
import com.cris.citizen.entity.Household;
import com.cris.citizen.repository.CitizenRepository;
import com.cris.citizen.repository.HouseholdRepository;
import com.cris.citizen.service.CitizenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CitizenServiceImpl implements CitizenService {

    private final CitizenRepository citizenRepository;
    private final HouseholdRepository householdRepository;

    @Override
    public Citizen registerCitizen(Citizen citizen) {
        citizen.setCitizenId("CIV-" + UUID.randomUUID().toString().substring(0,8).toUpperCase());
        citizen.setResidencyStatus("ACTIVE");
        citizen.setCreatedAt(Instant.now());
        return citizenRepository.save(citizen);
    }

    @Override
    public Citizen findCitizenByCivicId(String civicId) {
        return citizenRepository.findByCitizenId(civicId)
                .orElseThrow(() -> new RuntimeException("Citizen not found"));
    }

    @Override
    public List<Citizen> searchCitizens(String name) {
        return citizenRepository.findByFullNameContainingIgnoreCase(name);
    }

    @Override
    public Household createHousehold(String headCitizenId, Map<String,String> relations) {

        Citizen head = citizenRepository.findByCitizenId(headCitizenId)
                .orElseThrow(() -> new RuntimeException("Head citizen not found"));

        Household household = Household.builder()
                .headCitizenId(headCitizenId)
                .address(head.getAddress())
                .relations(relations)
                .build();

        Household savedHousehold = householdRepository.save(household);

        relations.forEach((citizenId, role) -> {
            Citizen citizen = citizenRepository.findByCitizenId(citizenId)
                    .orElseThrow(() -> new RuntimeException("Citizen not found: " + citizenId));
            citizen.setHouseholdId(savedHousehold.getId());
            citizen.setRelationshipToHead(role);
            citizenRepository.save(citizen);
        });

        return savedHousehold;
    }

    @Override
    public List<Citizen> findFamilyMembers(String householdId) {
        return citizenRepository.findByHouseholdId(householdId);
    }

}
