package com.halfacode.spring_real_time_learning.beanfactory;

import org.springframework.stereotype.Component;

@Component
public class MessageService {
    public String getMessage() {
        return "Hello from MessageService!";
    }
}