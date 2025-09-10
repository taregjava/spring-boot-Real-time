package com.halfacode.spring_real_time_learning.service;

import com.halfacode.spring_real_time_learning.dto.PatientDTO;
import com.halfacode.spring_real_time_learning.entity.Patient;
import com.halfacode.spring_real_time_learning.mapper.PatientMapper;
import com.halfacode.spring_real_time_learning.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientDTO getPatientWithUpcomingAppointments(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        return patientMapper.toDto(patient, LocalDate.now());
    }
}
