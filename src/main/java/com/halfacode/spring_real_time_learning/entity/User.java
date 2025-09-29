package com.halfacode.spring_real_time_learning.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users_tbl")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String country;
    // getters/setters
}