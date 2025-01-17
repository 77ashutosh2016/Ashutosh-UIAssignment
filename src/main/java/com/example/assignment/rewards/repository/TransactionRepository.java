package com.example.assignment.rewards.repository;

import com.example.assignment.rewards.entity.CustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<CustomerTransaction, Long> {
    List<CustomerTransaction> findByCustomerCustomerId(Long customerId);
}
