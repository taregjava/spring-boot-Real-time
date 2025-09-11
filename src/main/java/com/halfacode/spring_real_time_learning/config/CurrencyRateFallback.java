package com.halfacode.spring_real_time_learning.config;

import com.halfacode.spring_real_time_learning.client.ICurrencyRateFeignClient;
import com.halfacode.spring_real_time_learning.dto.CurrencyRate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
public class CurrencyRateFallback implements ICurrencyRateFeignClient {

    @Override
    public CurrencyRate getCurrencyRate(String currency, LocalDate date) {
        log.error("CurrencyRate API failed for {} on {}, fallback triggered", currency, date);

        CurrencyRate fallbackRate = new CurrencyRate();
        fallbackRate.setCharCode(currency);
        fallbackRate.setName("Fallback Currency");
        fallbackRate.setNominal("1");
        fallbackRate.setNumCode("000");
        fallbackRate.setValue("0.00");
        return fallbackRate;
    }
}
