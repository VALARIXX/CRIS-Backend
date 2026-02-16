package com.cris.citizen.service;

import com.cris.citizen.entity.Citizen;
import com.cris.citizen.entity.Household;

import java.util.List;
import java.util.Map;

public interface CitizenService {

    Citizen registerCitizen(Citizen citizen);

    Citizen findCitizenByCivicId(String civicId);

    List<Citizen> searchCitizens(String name);

    Household createHousehold(String headCitizenId, Map<String,String> relations);

    List<Citizen> findFamilyMembers(String householdId);
}
