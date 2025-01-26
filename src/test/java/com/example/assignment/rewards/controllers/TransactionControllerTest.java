package com.example.assignment.rewards.controllers;
import com.example.assignment.rewards.dto.TransactionDTO;
import com.example.assignment.rewards.entity.CustomerTransaction;
import com.example.assignment.rewards.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TransactionControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionService transactionService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
    }

    @Test
    void testAddTransaction() throws Exception {
        // Arrange
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount(120.0);
        transactionDTO.setTransactionDate(LocalDate.now());
        transactionDTO.setCustomerId(1L);

        CustomerTransaction savedTransaction = new CustomerTransaction();
        savedTransaction.setTransactionId(1L);
        savedTransaction.setAmount(120.0);
        savedTransaction.setTransactionDate(LocalDate.now());

        when(transactionService.addTransaction(any(TransactionDTO.class))).thenReturn(savedTransaction);

        // Act & Assert
        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId").value(1L))
                .andExpect(jsonPath("$.amount").value(120.0));
    }
    @Test
    void testGetTransactionsByCustomer() throws Exception {
        // Arrange
        CustomerTransaction transaction = new CustomerTransaction();
        transaction.setTransactionId(1L);
        transaction.setAmount(120.0);
        transaction.setTransactionDate(LocalDate.now());

        when(transactionService.getTransactionsByCustomer(1L)).thenReturn(Collections.singletonList(transaction));

        // Act & Assert
        mockMvc.perform(get("/api/transactions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].transactionId").value(1L))
                .andExpect(jsonPath("$[0].amount").value(120.0));
    }

    @Test
    void testDeleteTransaction() throws Exception {
        // Act & Assert
        mockMvc.perform(delete("/api/transactions/1"))
                .andExpect(status().isNoContent());

        verify(transactionService, times(1)).deleteTransactionsByCustomer(1L);
    }
    @Test
    void testEditTransaction() throws Exception {
        // Arrange
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionId(1L);
        transactionDTO.setAmount(150.0);
        transactionDTO.setTransactionDate(LocalDate.now());
        transactionDTO.setCustomerId(1L);

        CustomerTransaction updatedTransaction = new CustomerTransaction();
        updatedTransaction.setTransactionId(1L);
        updatedTransaction.setAmount(150.0);
        updatedTransaction.setTransactionDate(LocalDate.now());

        when(transactionService.editTransaction(any(TransactionDTO.class))).thenReturn(updatedTransaction);

        // Act & Assert
        mockMvc.perform(put("/api/transactions/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId").value(1L))
                .andExpect(jsonPath("$.amount").value(150.0));
    }
}
