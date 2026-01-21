package com.halfacode.spring_real_time_learning.entity;

import jakarta.persistence.*;
import org.hibernate.envers.Audited;

import jakarta.persistence.*;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "orders")
@Audited
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(nullable = false)
    private Double amount;

    // Constructors
    public OrderEntity() {}

    public OrderEntity(String customerName, Double amount) {
        this.customerName = customerName;
        this.amount = amount;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
