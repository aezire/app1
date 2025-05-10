

package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<ApiResponse<String>> signup(@Valid @RequestBody User user) {
        String result = userService.registerUser(user);
        if (result.contains("successfully")) {
            return ResponseEntity.ok(new ApiResponse<>(true, null, result));
        }
        return ResponseEntity.badRequest().body(new ApiResponse<>(false, null, result));
    }

    @GetMapping("/page")
    public String getSignupPage() {
        return "redirect:/index.html";
    }
}
