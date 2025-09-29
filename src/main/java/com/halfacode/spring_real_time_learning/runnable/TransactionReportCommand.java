package com.halfacode.spring_real_time_learning.runnable;

import com.halfacode.spring_real_time_learning.dto.TransactionReportDto;
import com.halfacode.spring_real_time_learning.service.TransactionService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionReportCommand implements BusinessCommand<TransactionReportCommand.Input, List<TransactionReportDto>> {

    private final TransactionService transactionService;

    public TransactionReportCommand(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public List<TransactionReportDto> execute(Input input) {
        return transactionService.generateReport(input.getUserId(), input.getBaseCurrency());
    }

    // DTO for inputs
    public static class Input {
        private final Long userId;
        private final String baseCurrency;

        public Input(Long userId, String baseCurrency) {
            this.userId = userId;
            this.baseCurrency = baseCurrency;
        }
        public Long getUserId() { return userId; }
        public String getBaseCurrency() { return baseCurrency; }
    }
}
