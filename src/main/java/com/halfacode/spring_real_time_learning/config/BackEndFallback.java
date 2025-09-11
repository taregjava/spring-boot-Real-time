package com.halfacode.spring_real_time_learning.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BackEndFallback implements IBackEndFeignClient {
    @Override
    public ResponseEntity<String> create() {
        log.error("Create API Failed Fallback triggered");
        return ResponseEntity.ok("Create Fallback default response");
    }

    @Override
    public ResponseEntity<String> read() {
        log.error("Read API Failed Fallback triggered");
        return ResponseEntity.ok("Read Fallback default response");
    }
}