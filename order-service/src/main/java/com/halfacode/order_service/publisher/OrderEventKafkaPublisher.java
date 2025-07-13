package com.halfacode.order_service.publisher;

import com.halfacode.order_service.OrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEventKafkaPublisher {

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @Value("${order.event.topicName}")
    private String topicName;

    public void sendOrderEvent(OrderEvent orderEvent) {
        // Send event with orderId as key and orderEvent as value
        kafkaTemplate.send(topicName, orderEvent.getOrderId(), orderEvent);
    }
}
