package com.example.assignment.rewards.service;


import com.example.assignment.rewards.entity.Customer;
import com.example.assignment.rewards.repository.CustomerRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public CustomUserDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
//        List<GrantedAuthority> authorities = customer.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) // Ensure "ROLE_" prefix
//                .collect(Collectors.toList());
        List<SimpleGrantedAuthority> authorities = customer.getRoles().stream()
                .map(SimpleGrantedAuthority::new) // Directly map as roles are prefixed with "ROLE_"
                .toList();


        return new org.springframework.security.core.userdetails.User(
                customer.getEmail(),
                customer.getPassword(),
                authorities
        );
    }
}
