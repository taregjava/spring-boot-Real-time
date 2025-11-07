package com.halfacode.spring_real_time_learning.repository;

import com.halfacode.spring_real_time_learning.model.LicenseRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRequestRepository extends JpaRepository<LicenseRequest, Long> {
}
