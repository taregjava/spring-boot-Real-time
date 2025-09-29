package com.halfacode.spring_real_time_learning.service;

import com.halfacode.spring_real_time_learning.dto.TransactionReportDto;
import com.halfacode.spring_real_time_learning.repo.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionReportDto> generateReport(Long userId, String baseCurrency) {
        List<TransactionReportDto> report = transactionRepository.getUserTransactionReport(userId, baseCurrency);

        // Example of extra business logic: filter out very small transactions
        return report.stream()
                .filter(r -> r.getConvertedAmount() > 1.0)
                .collect(Collectors.toList());
    }
}

