package com.example.assignment.rewards.service;

import com.example.assignment.rewards.dto.CustomerDTO;
import com.example.assignment.rewards.entity.Customer;
import com.example.assignment.rewards.exception.ResourceNotFoundException;
import com.example.assignment.rewards.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer registerCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(customerDTO.getPassword()); // Ideally, hash the password
        return customerRepository.save(customer);
    }

    public Customer login(String email, String password) {
        return customerRepository.findByEmail(email)
                .filter(c -> c.getPassword().equals(password))
                .orElseThrow(() -> new ResourceNotFoundException("Invalid credentials"));
    }
}
