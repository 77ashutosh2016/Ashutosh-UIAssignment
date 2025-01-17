package com.example.assignment.rewards.service;

import com.example.assignment.rewards.entity.Customer;
import com.example.assignment.rewards.entity.CustomerTransaction;
import com.example.assignment.rewards.entity.RewardPoints;
import com.example.assignment.rewards.repository.RewardRepository;
import com.example.assignment.rewards.utility.RewardCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardService {
    @Autowired
    private RewardRepository rewardRepository;

    public void calculateAndSaveRewards(CustomerTransaction transaction) {
        int points = RewardCalculator.calculateRewardPoints(transaction.getAmount());
        RewardPoints reward = new RewardPoints();

        Customer customer=new Customer();
        customer.setCustomerId(transaction.getCustomer().getCustomerId());

        reward.setCustomer(customer);
        reward.setRewardMonth(transaction.getTransactionDate().getMonthValue());
        reward.setRewardyear(transaction.getTransactionDate().getYear());
        reward.setPoints(points);

        rewardRepository.save(reward);
    }

    public List<RewardPoints> getRewardsByCustomer(Long customerId) {
        return rewardRepository.findByCustomerCustomerId(customerId);
    }
}
