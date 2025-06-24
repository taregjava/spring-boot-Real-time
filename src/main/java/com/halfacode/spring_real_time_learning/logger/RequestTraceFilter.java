package com.halfacode.spring_real_time_learning.logger;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.annotation.WebFilter;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@WebFilter("/*")
public class RequestTraceFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, jakarta.servlet.ServletException {
        String traceId = UUID.randomUUID().toString();
        MDC.put("traceId", traceId); // store in MDC
        try {
            chain.doFilter(request, response);
        } finally {
            MDC.clear(); // avoid memory leaks
        }
    }
}