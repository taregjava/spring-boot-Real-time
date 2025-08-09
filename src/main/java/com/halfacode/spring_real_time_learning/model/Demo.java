package com.halfacode.spring_real_time_learning.model;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo {
    // Scoped value for user context (JDK 21+ / 22)
   // static final ScopedValue<String> USER = ScopedValue.newInstance();

    /*public static void main(String[] args) {
        List<String> users = List.of("Alice", "Bob", "Charlie", "David", "Eve");

        // Use virtual-thread-per-task executor (no need to clean up scoped value manually)
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (String user : users) {
                executor.submit(() ->
                        ScopedValue.where(USER, user).run(() -> {
                            System.out.printf("Initiating Thread for User: %s : %s%n", USER.get(), Thread.currentThread());
                            var stocks = new StockRepository().getStockSymbols();
                            System.out.printf("User: %s : Stocks: %s : %s%n", USER.get(), stocks, Thread.currentThread());
                        })
                );
            }
        }*/
   // }
}
