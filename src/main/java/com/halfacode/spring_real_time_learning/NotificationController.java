package com.halfacode.spring_real_time_learning;

import com.halfacode.spring_real_time_learning.dto.AppointmentReminderRequest;
import com.halfacode.spring_real_time_learning.entity.Appointment;
import com.halfacode.spring_real_time_learning.service.NotificationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    @PostMapping("/register-appointment")
    public String registerAppointment(@RequestBody Appointment appointment) {
        notificationService.registerAndSendReminder(appointment);
        return "Appointment registered and reminder scheduled.";
    }
    @PostMapping("/send-reminder")
    public String sendReminder(@RequestBody AppointmentReminderRequest request) {
        notificationService.processTodayReminders(request.getPatientId());
        return "Reminder request accepted. Email will be sent in background.";
    }
}
