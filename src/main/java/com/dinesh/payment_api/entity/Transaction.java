package com.dinesh.payment_api.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private String currency;

    private String merchantId;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Transaction() {}

    public Transaction(Double amount,
                       String currency,
                       String merchantId,
                       LocalDateTime createdAt,
                       User user) {
        this.amount = amount;
        this.currency = currency;
        this.merchantId = merchantId;
        this.createdAt = createdAt;
        this.user = user;
    }

    // ===== GETTERS =====

    public Long getId() { return id; }

    public Double getAmount() { return amount; }

    public String getCurrency() { return currency; }

    public String getMerchantId() { return merchantId; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public User getUser() { return user; }

    // ===== SETTERS =====

    public void setAmount(Double amount) { this.amount = amount; }

    public void setCurrency(String currency) { this.currency = currency; }

    public void setMerchantId(String merchantId) { this.merchantId = merchantId; }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public void setUser(User user) { this.user = user; }
}