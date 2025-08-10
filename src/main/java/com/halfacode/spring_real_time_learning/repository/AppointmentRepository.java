package com.halfacode.spring_real_time_learning.repository;

import com.halfacode.spring_real_time_learning.entity.Appointment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Find appointment by ID and date
    @Query("SELECT a FROM Appointment a WHERE a.id = :appointmentId AND a.date = :date")
    Optional<Appointment> findTodayAppointmentById(Long appointmentId, LocalDate date);

    @Modifying
    @Transactional
    @Query("UPDATE Appointment a SET a.emailSent = :status WHERE a.id = :id")
    void updateEmailSentStatus(Long id, boolean status);
}
