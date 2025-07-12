package com.halfacode.spring_real_time_learning.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private String item;
    private int quantity;
}
