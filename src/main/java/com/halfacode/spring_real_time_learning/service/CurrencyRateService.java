package com.halfacode.spring_real_time_learning.service;


import com.halfacode.spring_real_time_learning.client.ICurrencyRateFeignClient;
import com.halfacode.spring_real_time_learning.dto.CurrencyRate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CurrencyRateService {

    private final ICurrencyRateFeignClient currencyRateFeignClient;

    public CurrencyRate getCurrencyRate(String currency, LocalDate date) {
        return currencyRateFeignClient.getCurrencyRate(currency, date); // real API call
    }
}

