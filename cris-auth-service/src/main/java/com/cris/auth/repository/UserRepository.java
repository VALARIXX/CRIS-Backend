package com.cris.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cris.auth.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> getUserByEmail(String email);

	boolean existsByEmail(String email);

	boolean existsByPhoneNumber(String phoneNumber);

	boolean existsByAadharNumber(String aadharNumber);

}
