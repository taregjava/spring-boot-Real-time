package com.halfacode.shipping_service.service;

import com.halfacode.shipping_service.entity.OrderEvent;
import com.halfacode.shipping_service.enums.OrderStatus;
import com.halfacode.shipping_service.repository.OrderEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ShippingEventService {

    @Autowired
    private OrderEventRepository repository;

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    private static final String TOPIC = "shipping-events";

    @KafkaListener(topics = "order-events", groupId = "shipping-service")
    public void consumeOrderEvent(OrderEvent orderEvent) {
        if (orderEvent.getStatus().equals(OrderStatus.CONFIRMED)) {
            shipOrder(orderEvent.getOrderId());
        }
    }

    public void shipOrder(String orderId) {
        OrderEvent event = new OrderEvent(orderId, OrderStatus.SHIPPED, "Order Shipped successfully", LocalDateTime.now());
        saveAndPublishShippingEvent(event);
    }

    public void deliverOrder(String orderId) {
        OrderEvent event = new OrderEvent(orderId, OrderStatus.DELIVERED, "Order delivered successfully", LocalDateTime.now());
        saveAndPublishShippingEvent(event);
    }

    private void saveAndPublishShippingEvent(OrderEvent event) {
        repository.save(event);
        kafkaTemplate.send(TOPIC, event.getOrderId(), event);
        System.out.println("Published shipping event for orderId: " + event.getOrderId());
    }
}
