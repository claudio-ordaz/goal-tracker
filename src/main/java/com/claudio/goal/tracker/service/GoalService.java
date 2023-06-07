package com.claudio.goal.tracker.service;

import java.util.List;
import java.util.Optional;

import com.claudio.goal.tracker.models.Goal;

public interface GoalService {

    Optional<Goal> getGoalById(int id);

    List<Goal> getAllGoals();

    void createGoal(Goal goal);

    void updateGoal(Goal goal);

    void deleteGoal(int id);
}
