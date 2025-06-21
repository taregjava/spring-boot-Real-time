package com.halfacode.spring_real_time_learning.service;

import com.halfacode.spring_real_time_learning.dto.OrderDto;
import com.halfacode.spring_real_time_learning.dto.OrderSearchDto;
import com.halfacode.spring_real_time_learning.mapper.OrderMapper;
import com.halfacode.spring_real_time_learning.model.Order;
import com.halfacode.spring_real_time_learning.repository.OrderRepository;
import com.halfacode.spring_real_time_learning.specification.OrderSpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderSpecificationBuilder specBuilder;
    private final OrderMapper orderMapper;

    public List<OrderDto> searchOrders(OrderSearchDto dto) {
        var spec = specBuilder.build(dto);
        List<Order> orders = orderRepository.findAll(spec);
        return orderMapper.toDtoList(orders);
    }
}