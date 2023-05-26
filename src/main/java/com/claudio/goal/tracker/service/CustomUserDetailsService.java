package com.claudio.goal.tracker.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.claudio.goal.tracker.models.User;
import com.claudio.goal.tracker.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
          User user = userRepository.findByUsername(username)
                 .orElseThrow(() ->
                         new UsernameNotFoundException("User not found with username or email: "+ username));

        Set<GrantedAuthority> authorities = new HashSet<>();

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                authorities);
    }
}
