package com.example.assignment.rewards.service;

import com.example.assignment.rewards.dto.CustomerDTO;
import com.example.assignment.rewards.entity.Customer;
import com.example.assignment.rewards.exception.ResourceNotFoundException;
import com.example.assignment.rewards.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Customer registerCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(passwordEncoder.encode(customerDTO.getPassword())); // hash the password
        customer.setRoles(Arrays.asList("USER"));
        return customerRepository.save(customer);
    }

    public Customer login(String email, String password) {
        return customerRepository.findByEmail(email)
                .filter(c ->passwordEncoder.matches(password, c.getPassword()))
                .orElseThrow(() -> new ResourceNotFoundException("Invalid credentials"));
    }
}
