package com.halfacode.spring_real_time_learning.model;

import java.util.List;
import java.util.Map;

class StockRepository {
    private static final Map<String, List<String>> userStocks = Map.of(
            "Alice", List.of("AAPL", "GOOGL", "AMZN"),
            "Bob", List.of("MSFT", "TSLA"),
            "Charlie", List.of("AAPL", "AMZN"),
            "David", List.of("GOOGL", "MSFT"),
            "Eve", List.of("TSLA")
    );

    public List<String> getStockSymbols() {
        String user =null; // get scoped value
       // String user = Demo.USER.get(); // get scoped value
        System.out.printf("Inside getStockSymbols User: %s : %s%n", user, Thread.currentThread());
        return userStocks.getOrDefault(user, List.of());
    }
}