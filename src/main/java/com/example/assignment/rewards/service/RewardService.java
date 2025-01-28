package com.example.assignment.rewards.service;

import com.example.assignment.rewards.entity.Customer;
import com.example.assignment.rewards.entity.CustomerTransaction;
import com.example.assignment.rewards.entity.RewardPoints;
import com.example.assignment.rewards.repository.RewardRepository;
import com.example.assignment.rewards.repository.TransactionRepository;
import com.example.assignment.rewards.utility.RewardCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardService {
    @Autowired
    private RewardRepository rewardRepository;

   /* @Autowired
    private TransactionService transactionService;*/
    @Autowired
    private TransactionRepository transactionRepository;

    /*public RewardService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
*/
    public int calculateAndSaveRewards(CustomerTransaction transaction) {
        int points = RewardCalculator.calculateRewardPoints(transaction.getAmount());
        RewardPoints reward = new RewardPoints();

        Customer customer=new Customer();
        customer.setCustomerId(transaction.getCustomer().getCustomerId());

        reward.setCustomer(customer);
        reward.setRewardMonth(transaction.getTransactionDate().getMonthValue());
        reward.setRewardyear(transaction.getTransactionDate().getYear());
        reward.setPoints(points);

        rewardRepository.save(reward);
        return points;
    }

    public List<RewardPoints> getRewardsByCustomer(Long customerId) {
        return rewardRepository.findByCustomerCustomerId(customerId);
    }

//    public void deleteRewardsByCustomer(Long customerId){
//        rewardRepository.deleteById(customerId);
//
//    }

    

    public List<RewardPoints> getAllRewards() {
        return rewardRepository.findAll();
    }

    public Map<Long, Map<YearMonth, Integer>> calculateMonthlyRewards() {

        List<CustomerTransaction> transactions = transactionRepository.findAll();

        // Group transactions by customerId and YearMonth
        return transactions.stream()
                .collect(Collectors.groupingBy(
                        transaction -> transaction.getCustomer().getCustomerId(), // Get customerId from Customer entity
                        Collectors.groupingBy(
                                transaction -> YearMonth.from(transaction.getTransactionDate()), // Group by YearMonth
                                Collectors.summingInt(transaction -> RewardCalculator.calculateRewardPoints(transaction.getAmount())) // Calculate rewards
                        )
                ));
    }
}
