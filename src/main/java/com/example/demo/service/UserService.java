package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return "Email already exists";
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            return "Username already exists";
        }
        if (!user.isTermsAccepted()) {
            return "Terms and conditions must be accepted";
        }
        userRepository.save(user);
        return "User registered successfully";
    }
}