package com.example.assignment.rewards.service;

import com.example.assignment.rewards.dto.TransactionDTO;
import com.example.assignment.rewards.entity.Customer;
import com.example.assignment.rewards.entity.CustomerTransaction;
import com.example.assignment.rewards.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RewardService rewardService;

    public CustomerTransaction addTransaction(TransactionDTO transactionDTO) {
        CustomerTransaction transaction = new CustomerTransaction();
        Customer customer=new Customer();
        customer.setCustomerId(transactionDTO.getCustomerId());
        transaction.setCustomer(customer);
        transaction.setAmount(transactionDTO.getAmount());

        transaction.setTransactionDate(transactionDTO.getTransactionDate());

        CustomerTransaction savedTransaction = transactionRepository.save(transaction);

        rewardService.calculateAndSaveRewards(savedTransaction);

        return savedTransaction;
    }

    public List<CustomerTransaction> getTransactionsByCustomer(Long customerId) {
        return transactionRepository.findByCustomerCustomerId(customerId);
    }
}
