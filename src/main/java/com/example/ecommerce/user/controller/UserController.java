package com.example.ecommerce.user.controller;

import com.example.ecommerce.user.model.User;
import com.example.ecommerce.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // API -> register user
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    // API -> login
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        boolean isAuthenticated = userService.loginUser(username, password);
        if (isAuthenticated) {
            return ResponseEntity.ok("Login Successful! Welcome " + username);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials!");
        }
    }
}