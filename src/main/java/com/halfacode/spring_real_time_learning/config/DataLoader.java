package com.halfacode.spring_real_time_learning.config;

import com.halfacode.spring_real_time_learning.dto.OrderStatus;
import com.halfacode.spring_real_time_learning.model.Customer;
import com.halfacode.spring_real_time_learning.model.Order;
import com.halfacode.spring_real_time_learning.repository.CustomerRepository;
import com.halfacode.spring_real_time_learning.repository.OrderRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class DataLoader {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    private static final String[] CUSTOMER_NAMES = {
            "Ali", "Sara", "John", "Fatima", "Omar", "Emma", "Liam", "Sophia", "Zain", "Noor"
    };

    private final Random random = new Random();

    @PostConstruct
    public void init() {
        IntStream.range(1, 101).forEach(i -> {
            Customer customer = Customer.builder()
                    .name(CUSTOMER_NAMES[random.nextInt(CUSTOMER_NAMES.length)])
                    .email("user" + i + "@example.com")
                    .build();
            customerRepository.save(customer);

            IntStream.range(0, 5).forEach(j -> {
                Order order = Order.builder()
                        .orderDate(LocalDate.now().minusDays(random.nextInt(365)))
                        .status(OrderStatus.values()[random.nextInt(OrderStatus.values().length)])
                        .totalAmount(50 + (5000 * random.nextDouble()))
                        .customer(customer)
                        .build();
                orderRepository.save(order);
            });
        });
    }
}