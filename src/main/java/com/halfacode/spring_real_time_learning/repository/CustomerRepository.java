package com.halfacode.spring_real_time_learning.repository;

import com.halfacode.spring_real_time_learning.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
