package com.halfacode.shipping_service.repository;

import com.halfacode.shipping_service.entity.OrderEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderEventRepository extends MongoRepository<OrderEvent,String> {
}
