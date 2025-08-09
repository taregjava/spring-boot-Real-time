package com.halfacode.spring_real_time_learning.controller;

import com.halfacode.spring_real_time_learning.dto.OrderRequest;
import com.halfacode.spring_real_time_learning.model.Customer;
import com.halfacode.spring_real_time_learning.model.Order;
import com.halfacode.spring_real_time_learning.model.PaymentDetails;
import com.halfacode.spring_real_time_learning.model.Receipt;
import com.halfacode.spring_real_time_learning.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/process")
    public Receipt processOrder(@RequestBody OrderRequest request) {
        Order order = new Order(request.getOrderId(), request.getAmount(), "NEW");
        PaymentDetails payment = new PaymentDetails(request.getCardNumber(),
                request.getCardHolderName(), request.getExpiryDate());
        Customer customer = new Customer(request.getCustomerName(), request.getCustomerEmail());

        return orderService.processOrder(order, payment, customer);
    }
}

