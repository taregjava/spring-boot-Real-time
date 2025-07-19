package com.halfacode.patient_service.service;

import com.halfacode.patient.*;
import com.halfacode.patient_service.model.Patient;
import com.halfacode.patient_service.repository.PatientRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService extends PatientServiceGrpc.PatientServiceImplBase {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void registerPatient(PatientRegistrationRequest request, StreamObserver<PatientRegistrationResponse> responseObserver) {

        Patient patient= new Patient(
                null,
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPhone(),
                request.getAddress()
        );
        patient= patientRepository.save(patient);
        responseObserver.onNext(PatientRegistrationResponse.newBuilder()
                .setPatientId(patient.id()).build());
        responseObserver.onCompleted();
    }

    @Override
    public void getPatientDetails(PatientDetailsRequest request, StreamObserver<PatientDetails> responseObserver) {

      //  Optional<Patient> byId = patientRepository.findById(request.getPatientId());
       var patient = patientRepository.findById(request.getPatientId());

       if (patient.isPresent()){
           Patient patient1 = patient.get();
           responseObserver.onNext(PatientDetails.newBuilder()
                   .setPatientId(patient1.id())
                           .setFirstName(patient1.firstName())
                           .setLastName(patient1.lastName())
                           .setEmail(patient1.email())
                           .setPhone(patient1.phone())
                           .setAddress(patient1.address())
                   .build());
       }else{
           responseObserver.onError(Status.NOT_FOUND.withDescription("Patient not found").asRuntimeException());

        }
       responseObserver.onCompleted();
    }
}
