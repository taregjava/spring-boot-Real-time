package com.halfacode.spring_real_time_learning.test;

import com.halfacode.spring_real_time_learning.model.CrInfoReq;
import com.halfacode.spring_real_time_learning.model.CrVerificationResponseDTO;
import com.halfacode.spring_real_time_learning.service.CrCacheReaderService;
import com.halfacode.spring_real_time_learning.service.CrService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner run(CrService crService, CrCacheReaderService crCacheReaderService) {
        return args -> {
            String crNumber = "123456";

            // First call — triggers @Cacheable and stores result in Redis
            CrVerificationResponseDTO first = crService.verifyCrInfo(new CrInfoReq(crNumber));
            System.out.println("✅ First call result (and cached): " + first);

            // Second call — read directly from Redis using CrCacheReaderService
            CrVerificationResponseDTO cached = crCacheReaderService.getCrInfoFromCache(crNumber);
            System.out.println("📦 Manual cache fetch result: " + cached);
        };
    }
}