package com.halfacode.spring_real_time_learning.controller;

import io.camunda.zeebe.client.ZeebeClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ProcessController {

    private final ZeebeClient client;

    public ProcessController(ZeebeClient client) {
        this.client = client;
    }

    @GetMapping("/start-process")
    public String startProcess() {
        client.newCreateInstanceCommand()
                .bpmnProcessId("license_process")
                .latestVersion()
                .variables(Map.of("licenseType", "student"))
                .send()
                .join();
        return "✅ Process started successfully!";
    }
}