package com.claudio.goal.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claudio.goal.tracker.models.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.claudio.goal.tracker.repository.UserRepository;
import com.claudio.goal.tracker.service.UserService;

@RestController
public class UserController {
    
    // @Autowired
    // private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
