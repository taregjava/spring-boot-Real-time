package com.halfacode.spring_real_time_learning.repository;


import com.halfacode.spring_real_time_learning.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {}

