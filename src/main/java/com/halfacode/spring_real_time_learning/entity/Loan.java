package com.halfacode.spring_real_time_learning.entity;

import com.halfacode.spring_real_time_learning.dto.LoanApplicationDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String applicantName;
    private String nationalId;
    private double amount;
    private int creditScore;
    private String status;
    private LocalDateTime createdAt;

    public Loan() {}

    public Loan(LoanApplicationDto dto, int creditScore) {
        this.applicantName = dto.getApplicantName();
        this.nationalId = dto.getNationalId();
        this.amount = dto.getAmount();
        this.creditScore = creditScore;
        this.status = "APPROVED";
        this.createdAt = LocalDateTime.now();
    }

    // getters and setters
}