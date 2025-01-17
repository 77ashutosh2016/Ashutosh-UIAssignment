package com.example.assignment.rewards.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long customerId; //primary key

        private String name;

        private String email;

        @JsonIgnore
        private String password;

        private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerTransaction> transactions;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RewardPoints> rewardPoints;

    public List<RewardPoints> getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(List<RewardPoints> rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public List<CustomerTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<CustomerTransaction> transactions) {
        this.transactions = transactions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
