package com.halfacode.spring_real_time_learning.async;
import com.halfacode.spring_real_time_learning.model.PaymentDetails;
import com.halfacode.spring_real_time_learning.model.Receipt;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/ordersasync")
public class OrderControllerAsync {

    private final OrderServiceAsync orderService;

    public OrderControllerAsync(OrderServiceAsync orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/demo-async")
    public CompletableFuture<Receipt> processDemoOrderAsync(@RequestBody PaymentDetails payment) {
        return orderService.processDemoOrderAsync(payment);
    }
}
