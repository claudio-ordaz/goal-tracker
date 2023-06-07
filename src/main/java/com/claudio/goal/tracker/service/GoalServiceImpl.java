package com.claudio.goal.tracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.claudio.goal.tracker.models.Goal;
import com.claudio.goal.tracker.models.User;
import com.claudio.goal.tracker.repository.GoalRepository;
import com.claudio.goal.tracker.repository.UserRepository;

@Service
public class GoalServiceImpl implements GoalService {

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<Goal> getGoalById(int id) {
        return goalRepository.findById(id);
    }

    @Override
    public List<Goal> getAllGoals() {
        return goalRepository.findAll();
    }

    @Override
    public void createGoal(Goal goal) {
        Authentication currUser = SecurityContextHolder.getContext().getAuthentication();
        
        System.out.println(currUser.getName());

        if(currUser.isAuthenticated()) {
            String username = currUser.getName();
    
            User user = userRepository.findByUsername(username);
            
            
            Goal newGoal = new Goal();
            
            newGoal.setName(goal.getName());
            newGoal.setDescription(goal.getDescription());
            newGoal.setDue_date(goal.getDue_date());
            newGoal.setProgress(goal.getProgress());
            newGoal.setUser(user);
            
            System.out.println("User ID: " + user.getId());

            goalRepository.save(newGoal);

        } else {
            throw new AuthenticationCredentialsNotFoundException("Authentication required");
        }
    }

    @Override
    public void updateGoal(Goal goal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateGoal'");
    }

    @Override
    public void deleteGoal(int id) {
        goalRepository.deleteById(id);
    }

}
