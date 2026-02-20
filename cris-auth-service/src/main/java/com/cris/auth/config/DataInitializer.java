

package com.cris.auth.config;

import com.cris.auth.entity.User;
import com.cris.auth.entity.userRole;
import com.cris.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Hardcoded initialization removed
    }

    private void createAdminIfNotFound(String email, String password, String username, String aadhar, String phone) {
        if (userRepository.existsByEmail(email)) {
            System.out.println("Admin user already exists with email: " + email);
            return;
        }
        if (userRepository.existsByAadharNumber(aadhar)) {
            System.out.println(
                    "User already exists with Aadhar: " + aadhar + " (Skipping admin creation for " + email + ")");
            return;
        }
        if (userRepository.existsByPhoneNumber(phone)) {
            System.out.println(
                    "User already exists with Phone: " + phone + " (Skipping admin creation for " + email + ")");
            return;
        }

        User admin = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .username(username)
                .aadharNumber(aadhar)
                .phoneNumber(phone)
                .role(userRole.Admin)
                .build();
        userRepository.save(admin);
        System.out.println("Admin user created: " + email);
    }
}
