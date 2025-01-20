package com.example.assignment.rewards.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "rewards")
public class RewardPoints {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rewardId;
    private Integer rewardMonth;
    private Integer rewardyear;
    private Integer points;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "customer_id") // Maps the foreign key
    private Customer customer;

    public Integer getRewardMonth() {
        return rewardMonth;
    }

    public void setRewardMonth(Integer rewardMonth) {
        this.rewardMonth = rewardMonth;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }


    public Integer getRewardyear() {
        return rewardyear;
    }

    public void setRewardyear(Integer rewardyear) {
        this.rewardyear = rewardyear;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }



}
