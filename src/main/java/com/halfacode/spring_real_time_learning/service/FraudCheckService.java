package com.halfacode.spring_real_time_learning.service;

import com.halfacode.spring_real_time_learning.dto.LoanApplicationDto;
import org.springframework.stereotype.Service;

@Service
public class FraudCheckService {
    public boolean isFraudulent(LoanApplicationDto dto) {
        // Fake rule: reject if loan > 1,000,000
        return dto.getAmount() > 1_000_000;
    }
}
