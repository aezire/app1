

package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            logger.warn("Signup failed: Email {} already exists", user.getEmail());
            return "Email already exists";
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            logger.warn("Signup failed: Username {} already exists", user.getUsername());
            return "Username already exists";
        }
        if (!user.isTermsAccepted()) {
            logger.warn("Signup failed: Terms not accepted for user {}", user.getUsername());
            return "Terms and conditions must be accepted";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            userRepository.save(user);
            logger.info("User {} registered successfully", user.getUsername());
            return "User registered successfully";
        } catch (Exception e) {
            logger.error("Error registering user {}: {}", user.getUsername(), e.getMessage());
            return "Error registering user: " + e.getMessage();
        }
    }
}