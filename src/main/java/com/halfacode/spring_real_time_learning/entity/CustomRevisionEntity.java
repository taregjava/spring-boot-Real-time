package com.halfacode.spring_real_time_learning.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import jakarta.persistence.*;


@Entity
@Table(name = "revinfo")
@RevisionEntity
public class CustomRevisionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    private int id;

    @RevisionTimestamp
    private long timestamp;

    // getters
}
