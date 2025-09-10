package com.halfacode.spring_real_time_learning.controller;
import com.halfacode.spring_real_time_learning.dto.TokenValidationResponse;
import com.halfacode.spring_real_time_learning.util.MultiFieldTokenUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/multi-token")
public class MultiFieldTokenController {

    @GetMapping("/generate/{requestId}/{tenantId}/{userId}")
    public String generateToken(@PathVariable String requestId,
                                @PathVariable String tenantId,
                                @PathVariable String userId) {
        return MultiFieldTokenUtil.generateToken(requestId, tenantId, userId);
    }

    @GetMapping("/validate/{token}")
    public TokenValidationResponse validateToken(@PathVariable String token) {
        return MultiFieldTokenUtil.validateTokenObject(token);
    }

}
