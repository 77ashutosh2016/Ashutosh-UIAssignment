package com.example.assignment.rewards.controllers;

import com.example.assignment.rewards.entity.RewardPoints;
import com.example.assignment.rewards.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {
    @Autowired
    private RewardService rewardService;

    @GetMapping("/{customerId}")
    public ResponseEntity<List<RewardPoints>> getRewards(@PathVariable Long customerId) {
        return ResponseEntity.ok(rewardService.getRewardsByCustomer(customerId));
    }

    @GetMapping
    public Map<Long, Map<YearMonth, Integer>> getMonthlyRewards() {
        return rewardService.calculateMonthlyRewards();
    /*public ResponseEntity<List<RewardPoints>> getAllRewards() {
        return ResponseEntity.ok(rewardService.getAllRewards());
    }*/
    }
}
