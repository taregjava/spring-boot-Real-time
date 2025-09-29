package com.halfacode.spring_real_time_learning.repo;

import com.halfacode.spring_real_time_learning.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
}
