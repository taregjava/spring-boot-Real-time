package com.halfacode.spring_real_time_learning.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
public class OrderDto {
    private Long id;
    private LocalDate orderDate;
    private OrderStatus status;
    private Double totalAmount;
    private String customerName;
}