package com.claudio.goal.tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claudio.goal.tracker.models.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.claudio.goal.tracker.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if(userService.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }

        userService.createUser(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        try {
            Authentication authenticated = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authenticated);

            return new ResponseEntity<>("User logged in successfully", HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }

    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userService.getAllUsers();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }

    @GetMapping("/logout")
    public String logout() {
        return "Logout";
    }
}
