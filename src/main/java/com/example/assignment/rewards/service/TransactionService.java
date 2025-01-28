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

        int rewardPoint= rewardService.calculateAndSaveRewards(savedTransaction);
        transaction.setRewardPoint(rewardPoint);
        return savedTransaction;
    }

    public List<CustomerTransaction> getTransactionsByCustomer(Long customerId) {
        return transactionRepository.findByCustomerCustomerId(customerId);
    }

    public void deleteTransactionsByCustomer(Long transactionId) {
        transactionRepository.deleteById(transactionId);
        //rewardService.deleteRewardsByCustomer(transactionId);
    }

    public CustomerTransaction edit_Transaction(TransactionDTO transactionDTO) {
        CustomerTransaction transaction = new CustomerTransaction();
        Customer customer=new Customer();
        customer.setCustomerId(transactionDTO.getCustomerId());
        transaction.setCustomer(customer);
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setTransactionId(transactionDTO.getTransactionId());
        transaction.setTransactionDate(transactionDTO.getTransactionDate());
        CustomerTransaction updatedTransaction = transactionRepository.save(transaction);
        rewardService.calculateAndSaveRewards(updatedTransaction);
        return updatedTransaction;
    }
    public CustomerTransaction editTransaction(TransactionDTO transactionDTO) {
        CustomerTransaction transaction = new CustomerTransaction();
        Customer customer=new Customer();
        customer.setCustomerId(transactionDTO.getCustomerId());
        transaction.setCustomer(customer);
        transaction.setAmount(transactionDTO.getAmount());

        transaction.setTransactionDate(transactionDTO.getTransactionDate());
        transaction.setTransactionId(transactionDTO.getTransactionId());

        CustomerTransaction savedTransaction = transactionRepository.save(transaction);

        int rewardPoint= rewardService.calculateAndSaveRewards(savedTransaction);
        transaction.setRewardPoint(rewardPoint);
        return savedTransaction;
    }
}
