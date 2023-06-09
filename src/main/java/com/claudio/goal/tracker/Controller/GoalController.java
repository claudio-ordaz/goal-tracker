package com.claudio.goal.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claudio.goal.tracker.exceptions.AuthorizationException;
import com.claudio.goal.tracker.exceptions.ResourceNotFoundException;
import com.claudio.goal.tracker.models.Goal;
import com.claudio.goal.tracker.repository.GoalRepository;
import com.claudio.goal.tracker.service.GoalService;

@RestController
@RequestMapping("/goal")
public class GoalController {

    @Autowired
    private GoalService goalService;

    // @Autowired
    // private GoalRepository goalRepository;

    @PostMapping("/createGoal")
    public ResponseEntity<String> createGoal(@RequestBody Goal goal) {
        goalService.createGoal(goal);
        return new ResponseEntity<String>("Goal created successfully", HttpStatus.OK);
    }

    @GetMapping("/getGoal")
    public void getGoal(@RequestBody int id) {
        if (goalService.findById(id) == null) {
            System.out.println("Goal does not exist by ID please enter a valid ID");
        } 

        goalService.findById(id);
    }

    @PutMapping("/updateGoal")
    public ResponseEntity<?> updateGoal(@RequestBody Goal goal) {
        try {
            goalService.updateGoal(goal);
            return new ResponseEntity<String>("Goal updated successfully", HttpStatus.OK);
        } catch (AuthorizationException e) {
            return new ResponseEntity<>("User not authorized to update goal", HttpStatus.UNAUTHORIZED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>("Goal not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/deleteGoal")
    public ResponseEntity<String> deleteGoal(@RequestBody int id) {
        goalService.deleteGoal(id);
        return new ResponseEntity<String>("Goal successsfully deleted", HttpStatus.OK);
    }
    
}
