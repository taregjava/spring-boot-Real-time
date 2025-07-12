package com.halfacode.spring_real_time_learning.entity;

import com.halfacode.spring_real_time_learning.dto.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String item;
    private int quantity;

    private String status; // e.g. "RECEIVED", "PROCESSED"
    private String details;
    private LocalDateTime eventTimestamp;
    private OrderStatus orderstatus;
}
