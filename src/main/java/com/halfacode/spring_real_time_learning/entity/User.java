package com.halfacode.spring_real_time_learning.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "users")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate birthDate;


    // Builder pattern (manual)
    public static class Builder {
        private final User user = new User();


        public Builder id(Long id) { user.id = id; return this; }
        public Builder firstName(String firstName) { user.firstName = firstName; return this; }
        public Builder lastName(String lastName) { user.lastName = lastName; return this; }
        public Builder email(String email) { user.email = email; return this; }
        public Builder phone(String phone) { user.phone = phone; return this; }
        public Builder birthDate(LocalDate birthDate) { user.birthDate = birthDate; return this; }
        public User build() { return user; }
    }


    public static Builder builder() { return new Builder(); }


    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
}

