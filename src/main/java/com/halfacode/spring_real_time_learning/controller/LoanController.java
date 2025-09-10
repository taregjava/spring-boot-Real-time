package com.halfacode.spring_real_time_learning.controller;

import com.halfacode.spring_real_time_learning.dto.LoanApplicationDto;
import com.halfacode.spring_real_time_learning.dto.LoanDecision;
import com.halfacode.spring_real_time_learning.service.LoanService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/apply")
    public LoanDecision apply(@RequestBody LoanApplicationDto dto) {
        return loanService.applyForLoan(dto);
    }
}