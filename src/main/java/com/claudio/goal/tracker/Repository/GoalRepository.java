package com.claudio.goal.tracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claudio.goal.tracker.Models.Goal;

public interface GoalRepository extends JpaRepository<Goal, Integer> {
    
}
