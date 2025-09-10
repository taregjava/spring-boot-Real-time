package com.halfacode.spring_real_time_learning.dto;

public class TokenValidationResponse {
    private boolean valid;
    private String requestId;
    private String tenantId;
    private String userId;
    private String message;

    public TokenValidationResponse() {}

    public TokenValidationResponse(boolean valid, String requestId, String tenantId, String userId, String message) {
        this.valid = valid;
        this.requestId = requestId;
        this.tenantId = tenantId;
        this.userId = userId;
        this.message = message;
    }

    // Getters and Setters
    public boolean isValid() { return valid; }
    public void setValid(boolean valid) { this.valid = valid; }

    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }

    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
