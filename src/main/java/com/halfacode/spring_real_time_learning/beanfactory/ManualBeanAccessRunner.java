package com.halfacode.spring_real_time_learning.beanfactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ManualBeanAccessRunner implements CommandLineRunner {

    private final ApplicationContext applicationContext;

    public ManualBeanAccessRunner(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) {
        // Access BeanFactory from ApplicationContext
        BeanFactory factory = applicationContext;

        // Manually get beans
        MessageService messageService = factory.getBean(MessageService.class);
        NotificationService notificationService = factory.getBean(NotificationService.class);

        String msg = messageService.getMessage();
        notificationService.notifyUser(msg);
    }
}