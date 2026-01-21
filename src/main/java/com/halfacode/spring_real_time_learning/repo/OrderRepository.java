package com.halfacode.spring_real_time_learning.repo;

import com.halfacode.spring_real_time_learning.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface OrderRepository extends
        JpaRepository<OrderEntity, Long>,
        RevisionRepository<OrderEntity, Long, Integer> {
}
