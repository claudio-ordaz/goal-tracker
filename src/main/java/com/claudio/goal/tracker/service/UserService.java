package com.claudio.goal.tracker.service;

import java.util.List;

import com.claudio.goal.tracker.models.User;


public interface UserService {
    
    User getUserById(int id);

    boolean existsByUsername(String username);

    List<User> getAllUsers();

    void createUser(User user);
}
