package com.example.assignment.rewards.services;
import com.example.assignment.rewards.dto.CustomerDTO;
import com.example.assignment.rewards.entity.Customer;
import com.example.assignment.rewards.exception.ResourceNotFoundException;
import com.example.assignment.rewards.repository.CustomerRepository;
import com.example.assignment.rewards.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterCustomer() {
        // Arrange
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("John Doe");
        customerDTO.setEmail("john.doe@example.com");
        customerDTO.setPassword("password123");

        Customer savedCustomer = new Customer();
        savedCustomer.setName("John Doe");
        savedCustomer.setEmail("john.doe@example.com");
        savedCustomer.setPassword("hashedPassword");

        when(passwordEncoder.encode("password123")).thenReturn("hashedPassword");
        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        // Act
        Customer result = customerService.registerCustomer(customerDTO);

        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("john.doe@example.com", result.getEmail());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void testLoginSuccess() {
        // Arrange
        Customer existingCustomer = new Customer();
        existingCustomer.setEmail("john.doe@example.com");
        existingCustomer.setPassword("hashedPassword");

        when(customerRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(existingCustomer));
        when(passwordEncoder.matches("password123", "hashedPassword")).thenReturn(true);

        // Act
        Customer result = customerService.login("john.doe@example.com", "password123");

        // Assert
        assertNotNull(result);
        assertEquals("john.doe@example.com", result.getEmail());
        verify(customerRepository, times(1)).findByEmail("john.doe@example.com");
    }

    @Test
    void testLoginFailure() {
        // Arrange
        when(customerRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> customerService.login("john.doe@example.com", "password123"));
        verify(customerRepository, times(1)).findByEmail("john.doe@example.com");
    }
}
