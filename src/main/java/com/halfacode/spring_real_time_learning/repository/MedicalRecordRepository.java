package com.halfacode.spring_real_time_learning.repository;

import com.halfacode.spring_real_time_learning.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {}

