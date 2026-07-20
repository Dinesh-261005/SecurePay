package com.dinesh.payment_api.service;

import com.dinesh.payment_api.entity.Transaction;
import com.dinesh.payment_api.entity.User;
import com.dinesh.payment_api.repository.TransactionRepository;
import com.dinesh.payment_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionServiceImpl(
            TransactionRepository transactionRepository,
            UserRepository userRepository
    ) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    // PAYMENT (UNCHANGED BEHAVIOR)
    @Override
    public Transaction makePayment(
            Long userId,
            Double amount,
            String currency,
            String merchantId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Transaction transaction = new Transaction(
                amount,
                currency,
                merchantId,
                LocalDateTime.now(),
                user
        );

        return transactionRepository.save(transaction);
    }

    // FIXED METHOD (ONLY CHANGE)
    @Override
    public List<Transaction> getTransactions(Long userId) {

        userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // Database-level filtering
        return transactionRepository.findTransactionsByUserId(userId);
    }
}