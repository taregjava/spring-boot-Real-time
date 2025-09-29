package com.halfacode.spring_real_time_learning.runnable;

import com.halfacode.spring_real_time_learning.entity.ExchangeRate;
import com.halfacode.spring_real_time_learning.entity.Transaction;
import com.halfacode.spring_real_time_learning.entity.User;
import com.halfacode.spring_real_time_learning.repo.ExchangeRateRepository;
import com.halfacode.spring_real_time_learning.repo.TransactionRepository;
import com.halfacode.spring_real_time_learning.repo.UserRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializerCommand implements BusinessCommand<Void, Long> {

    private final UserRepository userRepository;
    private final ExchangeRateRepository exchangeRateRepository;
    private final TransactionRepository transactionRepository;

    public DataInitializerCommand(UserRepository userRepository,
                                  ExchangeRateRepository exchangeRateRepository,
                                  TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.exchangeRateRepository = exchangeRateRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Long execute(Void input) {
        // Create User
        User user = new User();
        user.setName("John Doe");
        user.setCountry("USA");
        userRepository.save(user);

        // Create ExchangeRate
        ExchangeRate rate = new ExchangeRate();
        rate.setFromCurrency("EUR");
        rate.setToCurrency("USD");
        rate.setRate(1.1);
        rate.setActive(true);
        rate.setCreatedAt(LocalDateTime.now());
        exchangeRateRepository.save(rate);

        // Create Transaction
        Transaction tx = new Transaction();
        tx.setUser(user);
        tx.setExchangeRate(rate);
        tx.setCurrency("EUR");
        tx.setAmount(100.0);
        tx.setCreatedAt(LocalDateTime.now());
        transactionRepository.save(tx);

        return user.getId(); // return root id
    }
}
