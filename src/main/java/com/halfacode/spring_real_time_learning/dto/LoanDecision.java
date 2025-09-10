package com.halfacode.spring_real_time_learning.dto;

import lombok.Data;

@Data
public class LoanDecision {
    private boolean approved;
    private String message;
    private Long loanId;

    private LoanDecision(boolean approved, String message, Long loanId) {
        this.approved = approved;
        this.message = message;
        this.loanId = loanId;
    }

    public static LoanDecision approved(Long loanId) {
        return new LoanDecision(true, "Loan Approved", loanId);
    }

    public static LoanDecision rejected(String reason) {
        return new LoanDecision(false, reason, null);
    }
}
