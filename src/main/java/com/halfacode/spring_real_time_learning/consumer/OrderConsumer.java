package com.halfacode.spring_real_time_learning.consumer;

import com.halfacode.spring_real_time_learning.dto.OrderRequest;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    @JmsListener(destination = "order.queue")
    public void receiveOrder(OrderRequest orderRequest) {
        System.out.println("✅ Order received: " + orderRequest.getItem() + " x " + orderRequest.getQuantity());
    }
}