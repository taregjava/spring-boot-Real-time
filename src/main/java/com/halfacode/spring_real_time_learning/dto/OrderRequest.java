package com.halfacode.spring_real_time_learning.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private String orderId;
    private double amount;
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String customerName;
    private String customerEmail;
}