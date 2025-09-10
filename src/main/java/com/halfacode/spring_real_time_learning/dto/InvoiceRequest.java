package com.halfacode.spring_real_time_learning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class InvoiceRequest {
    private String customerName;
    private double subtotal;
}