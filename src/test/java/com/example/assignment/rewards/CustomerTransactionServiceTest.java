package com.example.assignment.rewards;
//
//
//import com.example.assignment.rewards.dto.TransactionDTO;
//import com.example.assignment.rewards.entity.Customer;
//import com.example.assignment.rewards.entity.CustomerTransaction;
//import com.example.assignment.rewards.repository.CustomerRepository;
//import com.example.assignment.rewards.repository.TransactionRepository;
//import com.example.assignment.rewards.service.RewardService;
//import com.example.assignment.rewards.service.TransactionService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//
//import java.util.Optional;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
class CustomerTransactionServiceTest {
//
//    @InjectMocks
//    private TransactionService transactionService;
//
//    @Mock
//    private CustomerRepository customerRepository;
//
//    @Mock
//    private TransactionRepository transactionRepository;
//
//    @Mock
//    private RewardService rewardService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        // Mock authenticated user
//        SecurityContextHolder.getContext().setAuthentication(
//                new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
//                        new User("test@example.com", "password", List.of()), null)
//        );
//    }
//
//
//    @Test
//    void testAddTransaction() {
//        // Mock customer and transaction
//        Customer customer = new Customer();
//        customer.setCustomerId(1L);
//        customer.setName("TestUSer");
//        customer.setEmail("test@example.com");
//        customer.setPassword("Password");
//        CustomerTransaction transaction = new CustomerTransaction();//1L, customer, 120.0, null
//        transaction.setTransactionId(1L);
//        transaction.setCustomer(customer);
//        transaction.setAmount(120.0);
//        when(customerRepository.findByEmail("test@example.com")).thenReturn(Optional.of(customer));
//        when(transactionRepository.save(transaction)).thenReturn(transaction);
//        when(rewardService.calculateAndSaveRewards(transaction).
//        TransactionDTO transactionDTO =new TransactionDTO();
//        transactionDTO.setAmount(120.0);
//        transactionDTO.setCustomerId(1L);
//        // Call the method
//        CustomerTransaction result = transactionService.addTransaction(transactionDTO);
//
//        // Verify results
//        assertEquals(120.0, result.getAmount());
//        verify(transactionRepository, times(1)).save(transaction);
//    }
//
//    @Test
//    void testGetTransactionsByCustomer() {
//        // Mock customer and transactions
//        Customer customer = new Customer();
//        customer.setCustomerId(1L);
//        customer.setName("TestUSer");
//        customer.setEmail("test@example.com");
//        customer.setPassword("Password");
//        CustomerTransaction transaction = new CustomerTransaction();//1L, customer, 120.0, null
//        transaction.setTransactionId(1L);
//        transaction.setCustomer(customer);
//        transaction.setAmount(120.0);
//
//        when(customerRepository.findByEmail("test@example.com")).thenReturn(Optional.of(customer));
//        when(transactionRepository.findByCustomerCustomerId(1L)).thenReturn(List.of(transaction));
//
//        // Call the method
//        List<CustomerTransaction> transactions = transactionService.getTransactionsByCustomer(1L);
//
//        // Verify results
//        assertEquals(1, transactions.size());
//        verify(transactionRepository, times(1)).findByCustomerCustomerId(1L);
//    }
}
