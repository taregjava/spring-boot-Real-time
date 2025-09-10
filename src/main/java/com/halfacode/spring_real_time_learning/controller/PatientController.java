package com.halfacode.spring_real_time_learning.controller;

import com.halfacode.spring_real_time_learning.dto.PatientDTO;
import com.halfacode.spring_real_time_learning.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/flat/{id}")
    public ResponseEntity<PatientDTO> getFlattened(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientWithUpcomingAppointments(id));
    }

   /* @GetMapping("/full/{id}")
    public ResponseEntity<PatientFullDTO> getFull(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getFullPatient(id));
    }

    @GetMapping("/optimized/{id}")
    public ResponseEntity<PatientFullDTO> getOptimized(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getFullPatientOptimized(id));
    }*/
}
