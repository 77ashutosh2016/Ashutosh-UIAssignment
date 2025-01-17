package com.example.assignment.rewards.controllers;

import com.example.assignment.rewards.dto.CustomerDTO;
import com.example.assignment.rewards.dto.LoginRequestDTO;
import com.example.assignment.rewards.entity.Customer;
import com.example.assignment.rewards.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<Customer> register(@RequestBody @Valid CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.registerCustomer(customerDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<Customer> login(@RequestBody @Valid LoginRequestDTO loginRequest) {
        return ResponseEntity.ok(customerService.login(loginRequest.getEmail(), loginRequest.getPassword()));
    }
}