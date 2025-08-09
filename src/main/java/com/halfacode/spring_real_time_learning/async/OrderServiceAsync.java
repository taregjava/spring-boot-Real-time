package com.halfacode.spring_real_time_learning.async;
import com.halfacode.spring_real_time_learning.model.Customer;
import com.halfacode.spring_real_time_learning.model.Order;
import com.halfacode.spring_real_time_learning.model.PaymentDetails;
import com.halfacode.spring_real_time_learning.model.Receipt;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
@Service
public class OrderServiceAsync {




    // BiSupplier: Generates a demo order & customer
    private final BiSupplier<Order, Customer> orderAndCustomerGenerator =
            () -> new Pair<>(
                    new Order(UUID.randomUUID().toString(), 99.99, "NEW"),
                    new Customer("Demo User", "demo@example.com")
            );

    // BiFunction: Processes payment
    private final BiFunction<Order, PaymentDetails, Receipt> paymentProcessor =
            (order, payment) -> {
                try {
                    // Simulate API delay
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                order.setStatus("PAID");
                return new Receipt(UUID.randomUUID().toString(), order.getOrderId(),
                        "Payment successful for amount $" + order.getTotalAmount());
            };

    // BiConsumer: Sends notification
    private final BiConsumer<Order, Customer> orderNotifier =
            (order, customer) -> {
                try {
                    // Simulate email delay
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("📧 Email to " + customer.getEmail() +
                        ": Your order " + order.getOrderId() + " is " + order.getStatus());
            };

    public CompletableFuture<Receipt> processDemoOrderAsync(PaymentDetails payment) {
        // Step 1: Generate Order + Customer
        Pair<Order, Customer> orderCustomer = orderAndCustomerGenerator.get();
        Order order = orderCustomer.getFirst();
        Customer customer = orderCustomer.getSecond();

        // Step 2: Process payment asynchronously
        CompletableFuture<Receipt> paymentFuture = CompletableFuture.supplyAsync(() ->
                paymentProcessor.apply(order, payment)
        );

        // Step 3: Send notification after payment completes (parallel)
        paymentFuture.thenRunAsync(() ->
                orderNotifier.accept(order, customer)
        );

        // Step 4: Return receipt future
        return paymentFuture;
    }
}