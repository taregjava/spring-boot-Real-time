package com.halfacode.spring_real_time_learning.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderSearchDto {
    private String customerName;
    private OrderStatus status;
    private Double minTotalAmount;
    private LocalDate startDate;
    private LocalDate endDate;
}
