package com.halfacode.spring_real_time_learning.beanfactory;

import org.springframework.stereotype.Component;

@Component
public class NotificationService {
    public void notifyUser(String message) {
        System.out.println("Notification sent: " + message);
    }
}