package com.halfacode.spring_real_time_learning.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "backend-api", url = "${cypcode.external-service.backend-api.url}",
        fallback = BackEndFallback.class)
public interface IBackEndFeignClient {

    @PostMapping
    ResponseEntity<String> create();

    @GetMapping
    ResponseEntity<String> read();
}
