package com.halfacode.spring_real_time_learning.worker;


import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class LicenseCreationWorker {

    // This worker handles tasks of type "LicenseCreation"
    @JobWorker(type = "LicenseCreation", name = "createLicenseJob")
    public void createLicense(final JobClient client, final ActivatedJob job) {
        System.out.println("Processing job: " + job.getKey() + " for process instance " + job.getProcessInstanceKey());

        // *** YOUR BUSINESS LOGIC GOES HERE ***
        // Example: call another microservice, interact with a database, etc.

        System.out.println("Successfully created license. Completing job.");

        // Complete the task in Camunda
        client.newCompleteCommand(job.getKey())
                .send()
                .join();
    }
}