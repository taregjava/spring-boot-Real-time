package com.halfacode.spring_real_time_learning.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ExchangeRate {
    @Id
    @GeneratedValue
    private Long id;

    private String fromCurrency;
    private String toCurrency;
    private Double rate;
    private boolean active;
    private LocalDateTime createdAt;

}
