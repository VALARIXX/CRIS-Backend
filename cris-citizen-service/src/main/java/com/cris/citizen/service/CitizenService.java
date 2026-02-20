package com.cris.citizen.service;

import com.cris.citizen.entity.Citizen;
import com.cris.citizen.entity.Household;

import java.util.List;
import java.util.Map;

public interface CitizenService {

    Citizen registerCitizen(Citizen citizen);

    Citizen findCitizenByCivicId(String civicId);

    Citizen findCitizenByAadhar(String aadhar);

    List<Citizen> searchCitizens(String query);

    Household createHousehold(String headCitizenId, Map<String, String> relations);

    List<Citizen> findFamilyMembers(String householdId);

    Citizen updateCitizen(String citizenId, Citizen citizen);
}
