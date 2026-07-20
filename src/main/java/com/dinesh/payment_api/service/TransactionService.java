package com.dinesh.payment_api.service;

import com.dinesh.payment_api.entity.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction makePayment(
            Long userId,
            Double amount,
            String currency,
            String merchantId
    );

    List<Transaction> getTransactions(Long userId);
}