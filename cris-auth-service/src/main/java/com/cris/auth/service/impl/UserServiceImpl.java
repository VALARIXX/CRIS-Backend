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
		return saveUser(body, userRole.Citizen);
	}

	@Override
	public ResponseEntity<?> adminCreateUser(Map<String, String> body) {
		userRole role = userRole.Citizen;
		if (body.containsKey("role")) {
			String roleStr = body.get("role");
			try {
				if ("OFFICER".equalsIgnoreCase(roleStr)) {
					role = userRole.Officer;
				} else if ("ADMIN".equalsIgnoreCase(roleStr)) {
					role = userRole.Admin;
				} else {
					role = userRole.valueOf(roleStr);
				}
			} catch (IllegalArgumentException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid role: " + roleStr);
			}
		}
		return saveUser(body, role);
	}

	private ResponseEntity<String> saveUser(Map<String, String> body, userRole role) {
		String username = body.get("username");
		String email = body.get("email");
		String phoneNumber = body.get("phoneNumber");
		String aadharNumber = body.get("aadharNumber");
		String password = body.get("password");

		if (userRepository.existsByEmail(email)) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Email already exists");
		}

		if (userRepository.existsByPhoneNumber(phoneNumber)) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Phone number already registered");
		}

		if (userRepository.existsByAadharNumber(aadharNumber)) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Aadhar number already registered");
		}

		String encodedPassword = passwordEncoder.encode(password);

		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPhoneNumber(phoneNumber);
		user.setAadharNumber(aadharNumber);
		user.setPassword(encodedPassword);
		user.setRole(role);
		user.setLastLogin(null);

		userRepository.save(user);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body("User created successfully");
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
				"username", user.getUsername(),
				"id", user.getId(),
				"email", user.getEmail(),
				"aadharNumber", user.getAadharNumber()));
	}

	@Override
	public ResponseEntity<?> getAllUsers() {
		return ResponseEntity.ok(userRepository.findAll());
	}

	@Override
	public ResponseEntity<?> updateUser(Long id, Map<String, String> body) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found"));

		if (body.containsKey("username"))
			user.setUsername(body.get("username"));
		if (body.containsKey("email"))
			user.setEmail(body.get("email"));
		if (body.containsKey("role"))
			user.setRole(userRole.valueOf(body.get("role")));
		if (body.containsKey("password") && !body.get("password").isEmpty()) {
			user.setPassword(passwordEncoder.encode(body.get("password")));
		}

		userRepository.save(user);
		return ResponseEntity.ok("User updated successfully");
	}

	@Override
	public ResponseEntity<?> deleteUser(Long id) {
		if (!userRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		userRepository.deleteById(id);
		return ResponseEntity.ok("User deleted successfully");
	}
}
