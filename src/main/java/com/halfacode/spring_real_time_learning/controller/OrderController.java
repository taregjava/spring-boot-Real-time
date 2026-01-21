package com.halfacode.spring_real_time_learning.controller;

import com.halfacode.spring_real_time_learning.entity.OrderEntity;
import com.halfacode.spring_real_time_learning.service.OrderService;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderEntity> create(@RequestBody OrderEntity order) {
        return ResponseEntity.ok(orderService.create(order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderEntity> update(
            @PathVariable Long id,
            @RequestBody OrderEntity order) {
        return ResponseEntity.ok(orderService.update(id, order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getById(id));
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<Revisions<Integer, OrderEntity>> history(
            @PathVariable Long id) {
        return ResponseEntity.ok(orderService.getHistory(id));
    }

    @GetMapping("/{id}/history/{revision}")
    public ResponseEntity<Revision<Integer, OrderEntity>> historyByRevision(
            @PathVariable Long id,
            @PathVariable Integer revision) {
        return ResponseEntity.ok(orderService.getRevision(id, revision));
    }
}
