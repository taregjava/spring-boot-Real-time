package com.halfacode.spring_real_time_learning.util;

import com.halfacode.spring_real_time_learning.entity.Appointment;
import com.halfacode.spring_real_time_learning.entity.Doctor;
import com.halfacode.spring_real_time_learning.entity.MedicalRecord;
import com.halfacode.spring_real_time_learning.entity.Patient;
import com.halfacode.spring_real_time_learning.repository.AppointmentRepository;
import com.halfacode.spring_real_time_learning.repository.DoctorRepository;
import com.halfacode.spring_real_time_learning.repository.MedicalRecordRepository;
import com.halfacode.spring_real_time_learning.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final PatientRepository patientRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public void run(String... args) {
        // Create doctors
        Doctor drSmith = new Doctor();
        drSmith.setId(1L);
        drSmith.setName("Dr. Smith");
        drSmith.setSpecialty("Cardiology");

        Doctor drBrown = new Doctor();
        drBrown.setId(2L);
        drBrown.setName("Dr. Brown");
        drBrown.setSpecialty("Endocrinology");

        doctorRepository.saveAll(List.of(drSmith, drBrown));

        // Create patient
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("John Doe");
        patient.setNationalId("AE12345");

        // Link Many-to-Many
        patient.getDoctors().add(drSmith);
        patient.getDoctors().add(drBrown);
        drSmith.getPatients().add(patient);
        drBrown.getPatients().add(patient);

        // Create medical record
        MedicalRecord record = new MedicalRecord();
        record.setId(1L);
        record.setDiagnosis("Diabetes Type 2");
        record.setPatient(patient);
        patient.setMedicalRecord(record);

        // Create appointments
        Appointment app1 = new Appointment();
        app1.setId(1L);
        app1.setDate(LocalDate.now().plusDays(5));
        app1.setPatient(patient);
        app1.setDoctor(drSmith);

        Appointment app2 = new Appointment();
        app2.setId(2L);
        app2.setDate(LocalDate.now().minusDays(10));
        app2.setPatient(patient);
        app2.setDoctor(drBrown);

        patient.getAppointments().addAll(List.of(app1, app2));

        // Save all
        patientRepository.save(patient);
        medicalRecordRepository.save(record);
        appointmentRepository.saveAll(List.of(app1, app2));
    }
}
