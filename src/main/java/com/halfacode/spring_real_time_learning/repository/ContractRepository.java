package com.halfacode.spring_real_time_learning.repository;

import com.halfacode.spring_real_time_learning.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository  extends JpaRepository<Contract, Long> {
}
