package com.halfacode.spring_real_time_learning.specification;

import com.halfacode.spring_real_time_learning.dto.OrderStatus;
import com.halfacode.spring_real_time_learning.model.Order;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class OrderSpecification {

    public static Specification<Order> hasStatus(OrderStatus status) {
        return (root, query, cb) -> cb.equal(root.get("status"), status);
    }

    public static Specification<Order> hasCustomerName(String name) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.join("customer").get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Order> hasTotalAmountGreaterThan(Double amount) {
        return (root, query, cb) -> cb.greaterThan(root.get("totalAmount"), amount);
    }

    public static Specification<Order> dateBetween(LocalDate start, LocalDate end) {
        return (root, query, cb) -> cb.between(root.get("orderDate"), start, end);
    }
}
