package com.halfacode.patient_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("patient")
public record Patient(@Id Long id, String firstName, String lastName, String email, String phone,String address) {
}