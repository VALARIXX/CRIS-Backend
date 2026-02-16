package com.cris.citizen.repository;

import com.cris.citizen.entity.Household;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HouseholdRepository extends MongoRepository<Household,String> {
}
