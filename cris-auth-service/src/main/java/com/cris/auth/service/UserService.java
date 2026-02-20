package com.cris.auth.service;

import com.cris.auth.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {
    User getUserByEmail(String email);

    ResponseEntity<String> CreateUser(Map<String, String> body);

    ResponseEntity<?> loginUser(Map<String, String> body);

    ResponseEntity<?> getAllUsers();

    ResponseEntity<?> updateUser(Long id, Map<String, String> body);

    ResponseEntity<?> deleteUser(Long id);

    ResponseEntity<?> adminCreateUser(Map<String, String> body);
}
