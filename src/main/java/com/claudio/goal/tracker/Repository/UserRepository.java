package com.claudio.goal.tracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claudio.goal.tracker.Models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    
}
