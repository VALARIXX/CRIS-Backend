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
        citizen.setCitizenId("CIV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
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
    public Citizen findCitizenByAadhar(String aadhar) {
        return citizenRepository.findByAadharNumber(aadhar)
                .orElseThrow(() -> new RuntimeException("Citizen not found"));
    }

    @Override
    public List<Citizen> searchCitizens(String query) {
        return citizenRepository.searchByNameOrId(query);
    }

    @Override
    public Household createHousehold(String headCitizenId, Map<String, String> relations) {

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

    @Override
    public Citizen updateCitizen(String citizenId, Citizen updatedData) {
        Citizen existing = findCitizenByCivicId(citizenId);

        if (updatedData.getFullName() != null)
            existing.setFullName(updatedData.getFullName());
        if (updatedData.getAddress() != null)
            existing.setAddress(updatedData.getAddress());
        if (updatedData.getMobile() != null)
            existing.setMobile(updatedData.getMobile());
        if (updatedData.getOccupation() != null)
            existing.setOccupation(updatedData.getOccupation());
        if (updatedData.getFatherName() != null)
            existing.setFatherName(updatedData.getFatherName());
        if (updatedData.getMotherName() != null)
            existing.setMotherName(updatedData.getMotherName());
        if (updatedData.getMaritalStatus() != null)
            existing.setMaritalStatus(updatedData.getMaritalStatus());
        if (updatedData.getBloodGroup() != null)
            existing.setBloodGroup(updatedData.getBloodGroup());
        if (updatedData.getNationality() != null)
            existing.setNationality(updatedData.getNationality());
        if (updatedData.getAadharNumber() != null)
            existing.setAadharNumber(updatedData.getAadharNumber());
        if (updatedData.getPhotoUrl() != null)
            existing.setPhotoUrl(updatedData.getPhotoUrl());

        return citizenRepository.save(existing);
    }

}
