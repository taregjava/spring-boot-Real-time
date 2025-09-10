package com.halfacode.spring_real_time_learning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class InvoiceResponse {
    private String invoiceNo;
    private String customerName;
    private double subtotal;
    private double vat;
    private double total;
    private String trn;
    private String companyName;
    private String issueDate;
}