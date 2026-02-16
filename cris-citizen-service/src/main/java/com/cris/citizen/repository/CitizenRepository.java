package com.cris.citizen.repository;

import com.cris.citizen.entity.Citizen;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface CitizenRepository extends MongoRepository<Citizen,String> {
    Optional<Citizen> findByCitizenId(String citizenId);
    List<Citizen> findByFullNameContainingIgnoreCase(String name);
    List<Citizen> findByHouseholdId(String householdId);
}
