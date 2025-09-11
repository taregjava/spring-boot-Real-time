package com.halfacode.spring_real_time_learning.controller;


import com.halfacode.spring_real_time_learning.dto.CurrencyRate;
import com.halfacode.spring_real_time_learning.service.CurrencyRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CurrencyRateController {

    private final CurrencyRateService currencyRateService;

    @GetMapping("/currencyRate/{currency}/{date}")
    public Mono<CurrencyRate> getCurrencyRate(
            @PathVariable("currency") String currency,
            @DateTimeFormat(pattern = "dd-MM-yyyy") @PathVariable("date") LocalDate date) {

        log.info("getCurrencyRate, currency:{}, date:{}", currency, date);

        CurrencyRate rate = currencyRateService.getCurrencyRate(currency, date);

        log.info("rate:{}", rate);
        return Mono.just(rate);
    }
}
