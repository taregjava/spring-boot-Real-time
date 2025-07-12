package com.halfacode.spring_real_time_learning.controller;

import com.halfacode.spring_real_time_learning.dto.OrderRequest;
import com.halfacode.spring_real_time_learning.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public String createOrder(@RequestBody OrderRequest orderRequest) {
        orderService.submitOrder(orderRequest);
        return "Order submitted!";
    }
}
