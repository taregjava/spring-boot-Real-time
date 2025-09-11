package com.halfacode.spring_real_time_learning.client;

import com.halfacode.spring_real_time_learning.config.CurrencyRateFallback;
import com.halfacode.spring_real_time_learning.dto.CurrencyRate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/*
@FeignClient(
        name = "currency-api",
        url = "${halfacode.external-service.currency-api.url}", // points to localhost:8081/api/v1
        fallback = CurrencyRateFallback.class
)
public interface ICurrencyRateFeignClient {

    @GetMapping("/currencyRate/{currency}/{date}")
    CurrencyRate getCurrencyRate(
            @PathVariable("currency") String currency,
            @DateTimeFormat(pattern = "dd-MM-yyyy") @PathVariable("date") LocalDate date
    );
}*/


@FeignClient(
        name = "currency-api",
       // url = "http://localhost:8081/api/v1" ,    // points directly to backend
        url = "${halfacode.external-service.currency-api.url}",
        fallback = CurrencyRateFallback.class
)
public interface ICurrencyRateFeignClient {

    @GetMapping("/currencyRate/{currency}/{date}")
    CurrencyRate getCurrencyRate(
            @PathVariable("currency") String currency,
            @DateTimeFormat(pattern = "dd-MM-yyyy") @PathVariable("date") LocalDate date
    );
}
