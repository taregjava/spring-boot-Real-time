package com.halfacode.spring_real_time_learning.controller;

import com.halfacode.spring_real_time_learning.dto.TransactionReportDto;
import com.halfacode.spring_real_time_learning.runnable.TransactionReportCommand;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionReportCommand transactionReportCommand;

    public TransactionController(TransactionReportCommand transactionReportCommand) {
        this.transactionReportCommand = transactionReportCommand;
    }
    @GetMapping("/report/{userId}")
    public List<TransactionReportDto> getUserReport(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "USD") String baseCurrency) {

        return transactionReportCommand.execute(
                new TransactionReportCommand.Input(userId, baseCurrency)
        );
    }

/*@GetMapping("/report/{userId}")
    public List<TransactionReportDto> getUserReport(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "USD") String baseCurrency) {
        return transactionService.generateReport(userId, baseCurrency);
    }*/
}
