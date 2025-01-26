package com.example.assignment.rewards.controllers;

import com.example.assignment.rewards.dto.CustomerDTO;
import com.example.assignment.rewards.dto.LoginRequestDTO;
import com.example.assignment.rewards.entity.Customer;
import com.example.assignment.rewards.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CustomerControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
        objectMapper = new ObjectMapper();


    }



    @Test
    void testRegisterCustomer() throws Exception {
        // Arrange
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("John Doe");
        customerDTO.setEmail("john.doe@example.com");
        customerDTO.setPassword("password123");

        Customer savedCustomer = new Customer();
        savedCustomer.setName("John Doe");
        savedCustomer.setEmail("john.doe@example.com");

        when(customerService.registerCustomer(any(CustomerDTO.class))).thenReturn(savedCustomer);

        // Act & Assert
        mockMvc.perform(post("/api/customers/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    void testLoginCustomer() throws Exception {
        // Arrange
        LoginRequestDTO loginRequest = new LoginRequestDTO();
        loginRequest.setEmail("john.doe@example.com");
        loginRequest.setPassword("password123");

        Customer loggedInCustomer = new Customer();
        loggedInCustomer.setName("John Doe");
        loggedInCustomer.setEmail("john.doe@example.com");

        when(customerService.login("john.doe@example.com", "password123")).thenReturn(loggedInCustomer);

        // Act & Assert
        mockMvc.perform(post("/api/customers/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }
}