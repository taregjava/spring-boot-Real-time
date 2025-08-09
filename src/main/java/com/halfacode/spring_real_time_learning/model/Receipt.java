package com.halfacode.spring_real_time_learning.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {
    private String receiptId;
    private String orderId;
    private String message;

}
