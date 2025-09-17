package com.halfacode.spring_real_time_learning.controller;

import com.halfacode.spring_real_time_learning.dto.AppointmentDto;
import com.halfacode.spring_real_time_learning.entity.Appointment;
import com.halfacode.spring_real_time_learning.entity.User;
import com.halfacode.spring_real_time_learning.repository.AppointmentRepository;
import com.halfacode.spring_real_time_learning.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
@RequestMapping("/api")
public class UserController {
    private final UserRepository userRepo;
    private final AppointmentRepository appointmentRepo;


    public UserController(UserRepository userRepo, AppointmentRepository appointmentRepo) {
        this.userRepo = userRepo;
        this.appointmentRepo = appointmentRepo;
    }


    @GetMapping("/users")
    public List<User> users() {
        return userRepo.findAll();
    }


    @GetMapping("/appointments")
    public List<AppointmentDto> appointments() {
        return appointmentRepo.findAll()
                .stream()
                .map(a -> new AppointmentDto(
                        a.getId(),
                        a.getDoctorName(),
                        a.getDepartment(),
                        a.getDateTime(),
                        a.getUser().getFirstName() + " " + a.getUser().getLastName()
                ))
                .toList();
    }
}