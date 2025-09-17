package com.halfacode.spring_real_time_learning.service;

import com.github.javafaker.Faker;
import com.halfacode.spring_real_time_learning.entity.Appointment;
import com.halfacode.spring_real_time_learning.entity.User;
import com.halfacode.spring_real_time_learning.repository.AppointmentRepository;
import com.halfacode.spring_real_time_learning.repository.UserRepository;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Random;


@Component
public class DataSeeder {
    private final UserRepository userRepo;
    private final AppointmentRepository appointmentRepo;
    private final Faker faker = new Faker(new Locale("en-US"));
    private final Random random = new Random();


    public DataSeeder(UserRepository userRepo, AppointmentRepository appointmentRepo) {
        this.userRepo = userRepo;
        this.appointmentRepo = appointmentRepo;
    }


    public void seed() {
// Create 20 realistic users
        for (int i = 0; i < 20; i++) {
            User u = User.builder()
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .email(generateEmail())
                    .phone(faker.phoneNumber().cellPhone())
                    .birthDate(LocalDate.now().minusYears(18 + random.nextInt(50)))
                    .build();
            userRepo.save(u);
        }


// Create appointments randomly for users
        var users = userRepo.findAll();
        String[] departments = {"Cardiology", "Orthopedics", "Pediatrics", "Neurology", "Oncology"};
        for (var u : users) {
            int apptCount = 1 + random.nextInt(3); // 1-3 appointments per user
            for (int j = 0; j < apptCount; j++) {
                Appointment a = Appointment.builder()
                        .doctorName("Dr. " + faker.name().lastName())
                        .department(departments[random.nextInt(departments.length)])
                        .dateTime(LocalDateTime.now().plusDays(random.nextInt(60)).withHour(9 + random.nextInt(8)).withMinute(0))
                        .user(u)
                        .build();
                appointmentRepo.save(a);
            }
        }


        System.out.println("Seeded users: " + userRepo.count() + " appointments: " + appointmentRepo.count());
    }


    private String generateEmail() {
        String local = faker.internet().emailAddress();
// make emails lower-case and reasonable for business
        return local.toLowerCase();
    }
}