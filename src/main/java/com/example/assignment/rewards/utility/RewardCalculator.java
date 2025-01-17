package com.example.assignment.rewards.utility;


public class RewardCalculator {
    public static int calculateRewardPoints(double amount) {
        int points = 0;
        if (amount > 100) {
            points += 2 * (amount - 100);
            amount = 100;
        }
        if (amount > 50) {
            points += 1 * (amount - 50);
        }
        return points;
    }
}
