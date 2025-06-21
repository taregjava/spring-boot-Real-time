package com.halfacode.spring_real_time_learning.specification;

import com.halfacode.spring_real_time_learning.dto.OrderSearchDto;
import com.halfacode.spring_real_time_learning.model.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class OrderSpecificationBuilder {

    public Specification<Order> build(OrderSearchDto dto) {
        Specification<Order> spec = Specification.where(null);

        if (dto.getStatus() != null)
            spec = spec.and(OrderSpecification.hasStatus(dto.getStatus()));

        if (dto.getCustomerName() != null)
            spec = spec.and(OrderSpecification.hasCustomerName(dto.getCustomerName()));

        if (dto.getMinTotalAmount() != null)
            spec = spec.and(OrderSpecification.hasTotalAmountGreaterThan(dto.getMinTotalAmount()));

        if (dto.getStartDate() != null && dto.getEndDate() != null)
            spec = spec.and(OrderSpecification.dateBetween(dto.getStartDate(), dto.getEndDate()));

        return spec;
    }
}
