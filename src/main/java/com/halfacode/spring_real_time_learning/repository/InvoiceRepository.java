package com.halfacode.spring_real_time_learning.repository;

import com.halfacode.spring_real_time_learning.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}

