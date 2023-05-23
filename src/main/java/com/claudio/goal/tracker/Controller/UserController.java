package com.claudio.goal.tracker.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claudio.goal.tracker.Repository.UserRepository;

@RestController
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }
}
