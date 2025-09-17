package com.halfacode.spring_real_time_learning.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String doctorName;
    private String department;
    private LocalDateTime dateTime;


    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


    // Builder
    public static class Builder {
        private final Appointment a = new Appointment();
        public Builder id(Long id) { a.id = id; return this; }
        public Builder doctorName(String d) { a.doctorName = d; return this; }
        public Builder department(String dep) { a.department = dep; return this; }
        public Builder dateTime(LocalDateTime dt) { a.dateTime = dt; return this; }
        public Builder user(User u) { a.user = u; return this; }
        public Appointment build() { return a; }
    }
    public static Builder builder() { return new Builder(); }


    // getters / setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
