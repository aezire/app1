package com.Main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Main.Model.User;
import com.Main.Repository.UserRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")  // Enable CORS for frontend testing
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        userRepository.save(user);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public User login(@RequestBody User loginRequest) {
        User user = userRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if (user != null) {
            return user;
        } else {
            throw new RuntimeException("User not found");
        }
    }
}