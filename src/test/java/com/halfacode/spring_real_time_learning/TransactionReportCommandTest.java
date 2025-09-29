package com.halfacode.spring_real_time_learning;

import com.halfacode.spring_real_time_learning.dto.TransactionReportDto;
import com.halfacode.spring_real_time_learning.entity.ExchangeRate;
import com.halfacode.spring_real_time_learning.entity.Transaction;
import com.halfacode.spring_real_time_learning.entity.User;
import com.halfacode.spring_real_time_learning.repo.TransactionRepository;
import com.halfacode.spring_real_time_learning.runnable.TransactionReportCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class TransactionReportCommandTest {

    @Autowired
    private TransactionReportCommand transactionReportCommand;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void testGenerateReport() {
        // Arrange: Insert test data
        User user = new User();
        user.setName("John Doe");
        user.setCountry("USA");

        ExchangeRate rate = new ExchangeRate();
        rate.setFromCurrency("EUR");
        rate.setToCurrency("USD");
        rate.setRate(1.1);
        rate.setActive(true);

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setExchangeRate(rate);
        transaction.setCurrency("EUR");
        transaction.setAmount(100.0);
        transaction.setCreatedAt(LocalDateTime.now());

        transactionRepository.save(transaction);

        // Act
        List<TransactionReportDto> report = transactionReportCommand.execute(
                new TransactionReportCommand.Input(user.getId(), "USD")
        );


        assertFalse(report.isEmpty());
        assertEquals("John Doe", report.get(0).getUserName());
        assertEquals(110.0, report.get(0).getConvertedAmount());
    }
}

