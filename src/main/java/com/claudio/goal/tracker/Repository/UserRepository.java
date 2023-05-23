package com.claudio.goal.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claudio.goal.tracker.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    
}
