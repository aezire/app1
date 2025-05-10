package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/signin")
    public String loginPage() {
        return "redirect:/login.html"; // Maps to /static/login.html
    }

    @GetMapping("/welcome")
    public String welcomePage() {
        return "redirect:/welcome.html"; // Maps to /static/welcome.html
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@Valid @RequestBody LoginRequest loginRequest) {
        ApiResponse<String> response = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body(response);
    }
}