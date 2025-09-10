package com.halfacode.spring_real_time_learning.service;

import com.halfacode.spring_real_time_learning.dto.LoanApplicationDto;
import com.halfacode.spring_real_time_learning.dto.LoanDecision;
import com.halfacode.spring_real_time_learning.entity.Loan;
import com.halfacode.spring_real_time_learning.repository.LoanRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    private final CreditScoreService creditScoreService;
    private final FraudCheckService fraudCheckService;
    private final LoanRepository loanRepo;

    public LoanService(CreditScoreService creditScoreService,
                       FraudCheckService fraudCheckService,
                       LoanRepository loanRepo) {
        this.creditScoreService = creditScoreService;
        this.fraudCheckService = fraudCheckService;
        this.loanRepo = loanRepo;
    }

    @Transactional
    public LoanDecision applyForLoan(LoanApplicationDto dto) {
        // Rule 1: Basic validation
        if (dto.getApplicantName() == null || dto.getApplicantName().isBlank()) {
            return LoanDecision.rejected("Invalid applicant name");
        }

        // Rule 2: Credit score check
        int score = creditScoreService.getScore(dto.getNationalId());
        if (score < 650) {
            return LoanDecision.rejected("Low credit score");
        }

        // Rule 3: Fraud detection
        if (fraudCheckService.isFraudulent(dto)) {
            return LoanDecision.rejected("Fraud detected");
        }

        // Rule 4: Save loan
        Loan loan = new Loan(dto, score);
        loanRepo.save(loan);

        return LoanDecision.approved(loan.getId());
    }
}
