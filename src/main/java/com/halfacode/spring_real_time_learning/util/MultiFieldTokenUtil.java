package com.halfacode.spring_real_time_learning.util;


import com.halfacode.spring_real_time_learning.dto.TokenValidationResponse;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MultiFieldTokenUtil {

    private static final String SECRET_KEY = "mySuperSecretKey123"; // keep safe (e.g. in env var)

    // Generate token with multiple fields
    public static String generateToken(String requestId, String tenantId, String userId) {
        long timestamp = System.currentTimeMillis();
        String payload = requestId + ":" + tenantId + ":" + userId + ":" + timestamp;
        String signature = hmacSha256(payload, SECRET_KEY);
        String raw = payload + ":" + signature;
        return Base64.getUrlEncoder().withoutPadding()
                .encodeToString(raw.getBytes(StandardCharsets.UTF_8));
    }

    // Validate token and extract values
    public static String validateToken(String token) {
        try {
            byte[] decodedBytes = Base64.getUrlDecoder().decode(token);
            String decoded = new String(decodedBytes, StandardCharsets.UTF_8);

            String[] parts = decoded.split(":");
            if (parts.length != 5) {
                return "Invalid token format!";
            }

            String requestId = parts[0];
            String tenantId = parts[1];
            String userId = parts[2];
            String timestampStr = parts[3];
            String signature = parts[4];

            String payload = requestId + ":" + tenantId + ":" + userId + ":" + timestampStr;
            String expectedSignature = hmacSha256(payload, SECRET_KEY);

            if (!expectedSignature.equals(signature)) {
                return "Invalid signature! Token may be tampered.";
            }

            long timestamp = Long.parseLong(timestampStr);
            long now = System.currentTimeMillis();
            if (now - timestamp > 5 * 60 * 1000) { // 5 min expiry
                return "Token expired!";
            }

            return String.format("Valid token → requestId=%s, tenantId=%s, userId=%s",
                    requestId, tenantId, userId);

        } catch (Exception e) {
            return "Error validating token: " + e.getMessage();
        }
    }


    public static TokenValidationResponse validateTokenObject(String token) {
        try {
            byte[] decodedBytes = Base64.getUrlDecoder().decode(token);
            String decoded = new String(decodedBytes, StandardCharsets.UTF_8);

            String[] parts = decoded.split(":");
            if (parts.length != 5) {
                return new TokenValidationResponse(false, null, null, null, "Invalid token format!");
            }

            String requestId = parts[0];
            String tenantId = parts[1];
            String userId = parts[2];
            String timestampStr = parts[3];
            String signature = parts[4];

            String payload = requestId + ":" + tenantId + ":" + userId + ":" + timestampStr;
            String expectedSignature = hmacSha256(payload, SECRET_KEY);

            if (!expectedSignature.equals(signature)) {
                return new TokenValidationResponse(false, null, null, null, "Invalid signature! Token may be tampered.");
            }

            long timestamp = Long.parseLong(timestampStr);
            long now = System.currentTimeMillis();
            if (now - timestamp > 5 * 60 * 1000) { // 5 min expiry
                return new TokenValidationResponse(false, null, null, null, "Token expired!");
            }

            return new TokenValidationResponse(true, requestId, tenantId, userId, "Token is valid");

        } catch (Exception e) {
            return new TokenValidationResponse(false, null, null, null, "Error validating token: " + e.getMessage());
        }
    }
    private static String hmacSha256(String data, String secret) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey =
                    new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256_HMAC.init(secretKey);
            byte[] hash = sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Error while signing token", e);
        }
    }
}
