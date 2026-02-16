package com.cris.auth.service.impl;

import com.cris.auth.entity.User;
import com.cris.auth.entity.userRole;
import com.cris.auth.repository.UserRepository;
import com.cris.auth.service.UserService;
import com.cris.auth.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JWTUtils jwtUtils;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User getUserByEmail(String email) {
		return userRepository.getUserByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found"));
	}


	@Override
	public ResponseEntity<String> CreateUser(Map<String, String> body) {

		String username = body.get("username");
		String email = body.get("email");
		String phoneNumber = body.get("phoneNumber");
		String aadharNumber = body.get("aadharNumber");
		String password = body.get("password");

		if (userRepository.getUserByEmail(email).isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Email already exists");
		}

		String encodedPassword = passwordEncoder.encode(password);

		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPhoneNumber(phoneNumber);
		user.setAadharNumber(aadharNumber);
		user.setPassword(encodedPassword);
		user.setRole(userRole.Citizen);
		user.setLastLogin(null);

		userRepository.save(user);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body("User registered successfully");
	}

	@Override
	public ResponseEntity<?> loginUser(Map<String, String> body) {

		String email = body.get("email");
		String password = body.get("password");

		Optional<User> optionalUser = userRepository.getUserByEmail(email);

		if (optionalUser.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("User not found");
		}

		User user = optionalUser.get();

		if (!passwordEncoder.matches(password, user.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("Invalid credentials");
		}

		user.setLastLogin(LocalDateTime.now());
		userRepository.save(user);

		String token = jwtUtils.generateToken(user.getEmail());

		return ResponseEntity.ok(Map.of(
				"token", token,
				"role", user.getRole(),
				"username", user.getUsername()
		));
	}
}
