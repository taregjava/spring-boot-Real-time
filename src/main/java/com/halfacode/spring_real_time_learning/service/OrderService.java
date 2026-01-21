package com.halfacode.spring_real_time_learning.service;

import com.halfacode.spring_real_time_learning.entity.OrderEntity;
import com.halfacode.spring_real_time_learning.repo.OrderRepository;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderEntity create(OrderEntity order) {
        return orderRepository.save(order);
    }

    public OrderEntity update(Long id, OrderEntity updated) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setCustomerName(updated.getCustomerName());
        order.setAmount(updated.getAmount());

        return orderRepository.save(order);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }


    @Transactional(readOnly = true)
    public OrderEntity getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Transactional(readOnly = true)
    public Revisions<Integer, OrderEntity> getHistory(Long orderId) {
        return orderRepository.findRevisions(orderId);
    }


    @Transactional(readOnly = true)
    public Revision<Integer, OrderEntity> getRevision(Long orderId, Integer revision) {
        return orderRepository.findRevision(orderId, revision)
                .orElseThrow(() -> new RuntimeException("Revision not found"));
    }

    public void printHistory(Long orderId) {
        orderRepository.findRevisions(orderId)
                .forEach(rev -> {
                    System.out.println(
                            "Revision: " + rev.getRevisionNumber() +
                                    " | Data: " + rev.getEntity()
                    );
                });
    }

}
