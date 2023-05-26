package com.claudio.goal.tracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claudio.goal.tracker.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

}
