package com.halfacode.spring_real_time_learning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "INVOICE_TBL")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName_en;
    private String customerName_ar;

    private String itemName_en;
    private String itemName_ar;

    private Double amount;
    private String invoiceDate;
}

