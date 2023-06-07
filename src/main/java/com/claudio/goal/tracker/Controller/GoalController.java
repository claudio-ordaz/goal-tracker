package com.claudio.goal.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claudio.goal.tracker.models.Goal;
import com.claudio.goal.tracker.service.GoalService;

@RestController
@RequestMapping("/api/goal")
public class GoalController {

    @Autowired
    private GoalService goalService;

    @PostMapping("/createGoal")
    public ResponseEntity<String> createGoal(@RequestBody Goal goal) {

        goalService.createGoal(goal);
        return new ResponseEntity<String>("Goal created successfully", HttpStatus.OK);
    }
    
}
