package com.halfacode.spring_real_time_learning.controller;

import com.halfacode.spring_real_time_learning.config.annotation.IsEmployee;
import com.halfacode.spring_real_time_learning.config.exception.SecurityErrorHandler;
import com.halfacode.spring_real_time_learning.entity.Person;
import com.halfacode.spring_real_time_learning.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.security.authorization.method.HandleAuthorizationDenied;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import java.util.List;

/*@RestController
@AuthorizeReturnObject
@RequiredArgsConstructor
@HandleAuthorizationDenied(handlerClass = SecurityErrorHandler.class)*/

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final PersonService personService;
    private final CompromisedPasswordChecker passwordChecker;
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    // @HandleAuthorizationDenied(handlerClass = SecurityErrorHandler.class)
    public String sayHelloAdmin() {
        return "Hello World Admin";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    //  @HandleAuthorizationDenied(handlerClass = SecurityErrorHandler.class)
    public String sayHelloUser() {
        return "Hello World User";
    }

    @GetMapping("/invited")
    @PreAuthorize("hasRole('INVITED')")
    // @HandleAuthorizationDenied(handlerClass = SecurityErrorHandler.class)
    public String sayHelloInvited() {
        return "Hello World Invited";
    }

    @GetMapping("/find")
    public Person findById() {
        return this.personService.findById().orElseThrow();
    }

    @GetMapping("/findAll")
    public List<Person> findAll() {
        return personService.findAll();
    }

   /* @GetMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password){
        return "";
    }*/
    @GetMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public String registerUser(@RequestParam String username, @RequestParam String password) {

        // Web: https://haveibeenpwned.com/Passwords
        CompromisedPasswordDecision decision = passwordChecker.check(password);

        if (decision.isCompromised()) {
            throw new IllegalArgumentException("Compromised Password.");
        }

        return String.format("User %s registered Successfully.", username);
    }
}