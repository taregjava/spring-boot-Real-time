package com.halfacode.spring_real_time_learning.controller;

import com.halfacode.spring_real_time_learning.dto.GetUserRequest;
import com.halfacode.spring_real_time_learning.dto.GetUserResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoint {

    private static final String NAMESPACE_URI =
            "http://example.com/soap/user";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetUserRequest")
    @ResponsePayload
    public GetUserResponse getUser(@RequestPayload GetUserRequest request) {

        GetUserResponse response = new GetUserResponse();
        response.setName("Tareg Safi");
        response.setEmail("tareg@example.com");

        return response;
    }
}
