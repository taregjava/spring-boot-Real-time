package com.halfacode.patient_service.repository;

import com.halfacode.patient_service.model.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient,Long> {
}
