package com.example.demo.service;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public ApiResponse<String> login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            logger.warn("Login attempt with empty credentials");
            return new ApiResponse<>(false, null, "Username or password cannot be empty");
        }

        try {
            Optional<User> userOptional = userRepository.findByUsername(username);
            if (userOptional.isPresent() && passwordEncoder.matches(password, userOptional.get().getPassword())) {
                String token = jwtUtil.generateToken(username);
                logger.info("Successful login for user: {}", username);
                return new ApiResponse<>(true, token, "Login successful");
            } else {
                logger.warn("Failed login attempt for user: {}", username);
                return new ApiResponse<>(false, null, "Invalid username or password");
            }
        } catch (Exception e) {
            logger.error("Error during login for user: {}", username, e);
            return new ApiResponse<>(false, null, "An error occurred during login");
        }
    }
}