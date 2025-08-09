package com.halfacode.spring_real_time_learning.service;

import com.halfacode.spring_real_time_learning.model.Customer;
import com.halfacode.spring_real_time_learning.model.Order;
import com.halfacode.spring_real_time_learning.model.PaymentDetails;
import com.halfacode.spring_real_time_learning.model.Receipt;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

@Service
public class OrderService {

    private final BiFunction<Order, PaymentDetails, Receipt> paymentProcessor =
            (order, payment) -> {
                // call a payment gateway API
                order.setStatus("PAID");
                return new Receipt(UUID.randomUUID().toString(), order.getOrderId(),
                        "Payment successful for amount $" + order.getTotalAmount());
            };

    private final BiConsumer<Order, Customer> orderNotifier =
            (order, customer) -> {
                //  integrate Email/SMS/Push Notification API
                System.out.println("Sending email to " + customer.getEmail() +
                        ": Your order " + order.getOrderId() + " is " + order.getStatus());
            };

    public Receipt processOrder(Order order, PaymentDetails payment, Customer customer) {
        Receipt receipt = paymentProcessor.apply(order, payment);
        orderNotifier.accept(order, customer);
        return receipt;
    }
}

