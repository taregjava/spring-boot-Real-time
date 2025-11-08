package com.halfacode.spring_real_time_learning.worker;

import io.camunda.zeebe.client.ZeebeClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class BpmnDeployer implements CommandLineRunner {

    private final ZeebeClient client;

    public BpmnDeployer(ZeebeClient client) {
        this.client = client;
    }

    @Override
    public void run(String... args) {
        client.newDeployResourceCommand()
                .addResourceFromClasspath("processes/license_process.bpmn")
                .send()
                .join();

        System.out.println("✅ license_process.bpmn deployed successfully");
    }
}
