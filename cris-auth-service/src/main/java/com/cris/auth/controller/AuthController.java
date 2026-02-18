package com.cris.auth.controller;

import java.util.Map;
import java.util.Optional;

import com.cris.auth.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cris.auth.entity.User;
import com.cris.auth.repository.UserRepository;
import com.cris.auth.service.impl.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Map<String, String> body) {
		return userServiceImpl.CreateUser(body);

	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody Map<String, String> body) {
		return userServiceImpl.loginUser(body);
	}
}