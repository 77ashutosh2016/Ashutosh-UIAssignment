package com.example.assignment.rewards.controllers;

import com.example.assignment.rewards.dto.TransactionDTO;
import com.example.assignment.rewards.entity.CustomerTransaction;
import com.example.assignment.rewards.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

//    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<CustomerTransaction> addTransaction(@RequestBody @Valid TransactionDTO transactionDTO) {
        return ResponseEntity.ok(transactionService.addTransaction(transactionDTO));
    }

    //   @PreAuthorize("hasRole('USER')")
    @GetMapping("/{customerId}")
    public ResponseEntity<List<CustomerTransaction>> getTransactions(@PathVariable Long customerId) {
        return ResponseEntity.ok(transactionService.getTransactionsByCustomer(customerId));
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{transactionId}")
        public ResponseEntity<List<CustomerTransaction>>deleteTransactions(@PathVariable Long transactionId) {
        transactionService.deleteTransactionsByCustomer(transactionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

   // @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<CustomerTransaction> editTransaction(@RequestBody @Valid TransactionDTO transactionDTO) {
        return ResponseEntity.ok(transactionService.editTransaction(transactionDTO));
    }


}
