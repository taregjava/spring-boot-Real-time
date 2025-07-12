package com.halfacode.spring_real_time_learning.service;

import com.halfacode.spring_real_time_learning.dto.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void submitOrder(OrderRequest orderRequest) {
        jmsTemplate.convertAndSend("order.queue", orderRequest);
    }
}
