package com.halfacode.appointment_service.config;

import com.halfacode.doctor.DoctorServiceGrpc;
import com.halfacode.patient.PatientServiceGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;

@Configuration
public class GrpcStub {

    @Bean
    DoctorServiceGrpc.DoctorServiceBlockingStub doctorServiceBlockingStub(GrpcChannelFactory channels) {
        return DoctorServiceGrpc.newBlockingStub(channels.createChannel("doctorService"));
    }

    @Bean
    PatientServiceGrpc.PatientServiceBlockingStub patientServiceBlockingStub(GrpcChannelFactory channels) {
        return PatientServiceGrpc.newBlockingStub(channels.createChannel("patientService"));
    }
}