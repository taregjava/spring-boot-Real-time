package com.halfacode.spring_real_time_learning.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException ex) {
        String message = ex.getMessage();

        if ("Compromised Password".equalsIgnoreCase(message)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Your password has been found in known data breaches. Please choose a more secure password.");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

}
