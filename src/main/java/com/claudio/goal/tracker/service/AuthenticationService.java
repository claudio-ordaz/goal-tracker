package com.claudio.goal.tracker.service;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.claudio.goal.tracker.configuration.JwtService;
import com.claudio.goal.tracker.controller.AuthenticationResponse;
import com.claudio.goal.tracker.controller.LoginRequest;
import com.claudio.goal.tracker.controller.RegisterRequest;
import com.claudio.goal.tracker.models.Role;
import com.claudio.goal.tracker.models.User;
import com.claudio.goal.tracker.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .build();

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
    }
    
    public AuthenticationResponse login(LoginRequest request) {
       authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken( 
            request.getUsername(),
            request.getPassword()
            )
        );

        User user = userRepository.findByUsername(request.getUsername());
        
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
    }
}
