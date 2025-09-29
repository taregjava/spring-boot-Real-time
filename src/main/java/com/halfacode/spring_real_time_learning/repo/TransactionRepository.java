package com.halfacode.spring_real_time_learning.repo;

import com.halfacode.spring_real_time_learning.dto.TransactionReportDto;
import com.halfacode.spring_real_time_learning.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT new com.halfacode.spring_real_time_learning.dto.TransactionReportDto(" +
            "u.name, u.country, t.currency, t.amount, " +
            "(t.amount * er.rate), t.createdAt) " +
            "FROM Transaction t " +
            "JOIN t.user u " +
            "JOIN t.exchangeRate er " +
            "WHERE er.active = true " +
            "AND er.toCurrency = :baseCurrency " +
            "AND u.id = :userId")
    List<TransactionReportDto> getUserTransactionReport(
            @Param("userId") Long userId,
            @Param("baseCurrency") String baseCurrency);
}
