package com.halfacode.order_service.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


@Configuration
public class EndpointLogger {

    private final RequestMappingHandlerMapping handlerMapping;

    public EndpointLogger(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    @PostConstruct
    public void logAllEndpoints() {
        handlerMapping.getHandlerMethods().forEach((key, value) ->
                System.out.println("Mapped: " + key + " -> " + value));
    }
}
