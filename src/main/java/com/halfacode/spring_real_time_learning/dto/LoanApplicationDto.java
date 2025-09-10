package com.halfacode.spring_real_time_learning.dto;

import lombok.Data;

@Data
public class LoanApplicationDto {
    private String applicantName;
    private String nationalId;
    private double amount;
}
