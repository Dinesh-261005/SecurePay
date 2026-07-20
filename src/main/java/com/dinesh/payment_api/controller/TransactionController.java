package com.dinesh.payment_api.controller;

import com.dinesh.payment_api.dto.ApiResponse;
import com.dinesh.payment_api.dto.PaymentRequest;
import com.dinesh.payment_api.entity.Transaction;
import com.dinesh.payment_api.service.TransactionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/payment")
    public ApiResponse<Transaction> makePayment(
            @RequestBody PaymentRequest request) {

        Transaction transaction =
                transactionService.makePayment(
                        request.getUserId(),
                        request.getAmount(),
                        request.getCurrency(),
                        request.getMerchantId());

        return new ApiResponse<>(
                true,
                "Payment Successful",
                transaction);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{userId}")
    public ApiResponse<List<Transaction>> getTransactions(
            @PathVariable Long userId) {

        List<Transaction> transactions =
                transactionService.getTransactions(userId);

        return new ApiResponse<>(
                true,
                "Transactions fetched",
                transactions);
    }
}