package com.halfacode.appointment_service.repository;

import com.halfacode.appointment_service.model.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
}