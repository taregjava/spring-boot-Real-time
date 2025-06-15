package com.halfacode.spring_real_time_learning.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    private boolean isDeleted = false;
    private boolean isFinalized = false;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Finalize the record (disable future updates)
    public void finalizeEntity() {
        this.isFinalized = true;
    }

    // Setters with update protection
    public void setName(String name) {
        if (isFinalized) {
            throw new UnsupportedOperationException("Update not allowed. This record is finalized.");
        }
        this.name = name;
    }

    public void setEmail(String email) {
        if (isFinalized) {
            throw new UnsupportedOperationException("Update not allowed. This record is finalized.");
        }
        this.email = email;
    }

    // Other setters...

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public boolean isDeleted() { return isDeleted; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public boolean isFinalized() { return isFinalized; }

    // Regular setters
    public void setId(Long id) { this.id = id; }
    public void setDeleted(boolean deleted) { isDeleted = deleted; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
