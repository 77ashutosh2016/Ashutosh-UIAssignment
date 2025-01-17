package com.example.assignment.rewards.repository;

import com.example.assignment.rewards.entity.RewardPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardRepository extends JpaRepository<RewardPoints, Long> {


    List<RewardPoints> findByCustomerCustomerId(Long customerId);
}
