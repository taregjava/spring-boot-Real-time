package com.halfacode.spring_real_time_learning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditLogEntry {
    private String event;
    private String userId;
    private String path;
    private String timestamp;
    private String details;
    private Object requestPayload;

    private Object responsePayload;

}