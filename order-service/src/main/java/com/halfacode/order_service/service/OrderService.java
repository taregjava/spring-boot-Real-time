package com.halfacode.order_service.service;


import com.halfacode.order_service.OrderEvent;
import com.halfacode.order_service.dto.OrderRequest;
import com.halfacode.order_service.dto.OrderResponse;
import com.halfacode.order_service.enums.OrderStatus;
import com.halfacode.order_service.publisher.OrderEventKafkaPublisher;
import com.halfacode.order_service.repository.OrderEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class OrderService {
    @Autowired
    private OrderEventRepository repository;

    @Autowired
    private OrderEventKafkaPublisher publisher;


    // Handle order creation
    public OrderResponse placeAnOrder(OrderRequest orderRequest) {
        String orderId = UUID.randomUUID().toString().split("-")[0];
        orderRequest.setOrderId(orderId);
        //do request validation and real business logic
        //save that event and publish kafka messages
        OrderEvent orderEvent=new OrderEvent(orderId, OrderStatus.CREATED,"Order created successfully", LocalDateTime.now());
        saveAndPublishEvents(orderEvent);
        return new OrderResponse(orderId, OrderStatus.CREATED);
    }

    // Handle order confirmation
    public OrderResponse confirmOrder(String orderId) {
        OrderEvent orderEvent=new OrderEvent(orderId,OrderStatus.CONFIRMED,"Order confirmed successfully", LocalDateTime.now());
        saveAndPublishEvents(orderEvent);
        return new OrderResponse(orderId, OrderStatus.CONFIRMED);
    }

    private void saveAndPublishEvents(OrderEvent orderEvent){
        try {
            repository.save(orderEvent);
        } catch (Exception e) {
            log.error("Failed to save order event", e);
            throw e;
        }

        try {
            publisher.sendOrderEvent(orderEvent);
        } catch (Exception e) {
            log.error("Failed to send order event to Kafka", e);
            throw e;
        }
    }



}
