package com.halfacode.spring_real_time_learning.runnable;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private final DataInitializerCommand initializer;

    public StartupRunner(DataInitializerCommand initializer) {
        this.initializer = initializer;
    }

    @Override
    public void run(String... args) {
        Long userId = initializer.execute(null);
        System.out.println("Initialized test data for User ID: " + userId);
    }
}
