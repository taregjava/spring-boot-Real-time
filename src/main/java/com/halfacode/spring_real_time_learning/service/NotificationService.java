package com.halfacode.spring_real_time_learning.service;

import com.halfacode.spring_real_time_learning.entity.Appointment;
import com.halfacode.spring_real_time_learning.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Transactional
    public void processTodayReminders(Long patientId) {
        System.out.println("Checking reminders for patient: " + patientId);

        sendAppointmentReminder(patientId);
    }
    @Transactional
    public void registerAndSendReminder(Appointment appointment) {
        appointmentRepository.save(appointment);
       sendAppointmentReminder(appointment.getId());
    }
    @Async("taskExecutor")
    public void sendAppointmentReminder(Long appointmentId) {
        Optional<Appointment> optionalAppt = appointmentRepository.findTodayAppointmentById(appointmentId, LocalDate.now());

        if (optionalAppt.isEmpty()) {
            System.out.println("No appointment found for today. Email not sent.");
            return;
        }
        Appointment appt = optionalAppt.get();

        String subject = "Appointment Reminder";
        String body = String.format(
                "Dear %s,\n\nThis is a reminder for your appointment with Dr. %s on %s at %s.\n\nThank you.",
                appt.getPatientName(),
                appt.getDoctorName(),
                appt.getDate(),
                appt.getTime()
        );

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("tareg@gmail.com");
        message.setTo(appt.getPatientEmail());
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
        System.out.println("Reminder email sent in thread: " + Thread.currentThread().getName());

        appointmentRepository.updateEmailSentStatus(appt.getId(), true);
    }
}
