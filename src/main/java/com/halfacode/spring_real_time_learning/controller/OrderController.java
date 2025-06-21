package com.halfacode.spring_real_time_learning.controller;

import com.halfacode.spring_real_time_learning.dto.OrderDto;
import com.halfacode.spring_real_time_learning.dto.OrderSearchDto;
import com.halfacode.spring_real_time_learning.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/search")
    public List<OrderDto> search(@RequestBody OrderSearchDto dto) {
        return orderService.searchOrders(dto);
    }
}