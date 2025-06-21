package com.halfacode.spring_real_time_learning.mapper;

import com.halfacode.spring_real_time_learning.dto.OrderDto;
import com.halfacode.spring_real_time_learning.model.Order;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "customer.name", target = "customerName")
    OrderDto toDto(Order order);

  /*  @Mapping(target = "customerName", expression = "java(order.getCustomer() != null ? order.getCustomer().getName() : null)")
    OrderDto toDto(Order order);*/
    List<OrderDto> toDtoList(List<Order> orders);
}