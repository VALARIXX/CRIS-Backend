package com.kce.census.Repository;

import com.kce.census.Entity.Officer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OfficerRepository extends MongoRepository<Officer, String> {
    Optional<Officer> findByEmail(String email);
}
