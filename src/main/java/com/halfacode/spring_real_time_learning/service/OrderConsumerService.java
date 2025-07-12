package com.halfacode.spring_real_time_learning.service;

import com.halfacode.spring_real_time_learning.dto.OrderRequest;
import com.halfacode.spring_real_time_learning.entity.OrderEntity;
import com.halfacode.spring_real_time_learning.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumerService {

    @Autowired
    private OrderRepository orderRepository;

    @JmsListener(destination = "order.queue")
    public void handleOrder(OrderRequest request) {
        System.out.println("🔔 Received order: " + request.getItem());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        OrderEntity entity = new OrderEntity();
        entity.setItem(request.getItem());
        entity.setQuantity(request.getQuantity());
        entity.setStatus("PROCESSED");

        orderRepository.save(entity);
        System.out.println("✅ Order saved: " + entity.getItem());
    }
}