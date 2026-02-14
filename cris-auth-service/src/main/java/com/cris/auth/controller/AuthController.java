package com.cris.auth.controller;

import java.util.Map;
import java.util.Optional;

import com.cris.auth.utils.JWTUtils;
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
	private final UserServiceImpl userServiceImpl;
	private  final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JWTUtils jwtUtils;
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Map<String, String> body) {
		String email=body.get("email");
		String password=body.get("password");
		if(userRepository.getUserByEmail(email).isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Email Al ready exists");
		}
		String encoded=passwordEncoder.encode(password);
		userServiceImpl.CreateUser(User.builder().email(email).password(encoded).build());

		return  ResponseEntity.ok("Successfully Created");
		
	}
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody Map<String,String> body){
		String email=body.get("email");
		String password= body.get("password");
		Optional<User> optionalUser=userRepository.getUserByEmail(email);
		if(optionalUser.isEmpty()) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not Found");
		}
		User user=optionalUser.get();
		if(!passwordEncoder.matches(password,user.getPassword()))
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Login");
        String token = jwtUtils.generateToken(email);
        return ResponseEntity.ok(Map.of("token",token));

    }
}
