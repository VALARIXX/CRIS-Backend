package com.cris.citizen.repository;

import com.cris.citizen.entity.Citizen;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface CitizenRepository extends MongoRepository<Citizen, String> {
    Optional<Citizen> findByCitizenId(String citizenId);

    Optional<Citizen> findByAadharNumber(String aadharNumber);

    @org.springframework.data.mongodb.repository.Query("{ '$or': [ { 'fullName': { '$regex': ?0, '$options': 'i' } }, { 'citizenId': { '$regex': ?0, '$options': 'i' } } ] }")
    List<Citizen> searchByNameOrId(String query);

    List<Citizen> findByHouseholdId(String householdId);
}
