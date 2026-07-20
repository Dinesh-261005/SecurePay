package com.dinesh.payment_api.dto;

import lombok.Data;

@Data
public class PaymentRequest {

    private Long userId;
    private Double amount;
    private String currency;
    private String merchantId;
}