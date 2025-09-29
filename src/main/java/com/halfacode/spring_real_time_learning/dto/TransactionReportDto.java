package com.halfacode.spring_real_time_learning.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionReportDto {
    private String userName;
    private String userCountry;
    private String transactionCurrency;
    private Double originalAmount;
    private Double convertedAmount; // in USD
    private LocalDateTime transactionDate;

    public TransactionReportDto(String userName, String userCountry,
                                String transactionCurrency, Double originalAmount,
                                Double convertedAmount, LocalDateTime transactionDate) {
        this.userName = userName;
        this.userCountry = userCountry;
        this.transactionCurrency = transactionCurrency;
        this.originalAmount = originalAmount;
        this.convertedAmount = convertedAmount;
        this.transactionDate = transactionDate;
    }


}
