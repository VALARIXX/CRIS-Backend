package com.kce.census.Repository;

import com.kce.census.Entity.Citizen;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CitizenRepository extends MongoRepository<Citizen, String> {
    List<Citizen> findByFirstNameContainingIgnoreCase(String name);
}
