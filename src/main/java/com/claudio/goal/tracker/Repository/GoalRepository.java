package com.claudio.goal.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claudio.goal.tracker.models.Goal;

public interface GoalRepository extends JpaRepository<Goal, Integer> {
    
}
