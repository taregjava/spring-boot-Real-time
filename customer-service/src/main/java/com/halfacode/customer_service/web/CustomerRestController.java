package com.halfacode.customer_service.web;

import com.halfacode.customer_service.entities.Customer;
import com.halfacode.customer_service.repository.CustomerRepository;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    private final CustomerRepository customerRepository;

    public CustomerRestController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customers")
    @PreAuthorize("hasAuthority('USER')")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id).orElseThrow();
    }

    @GetMapping("/mySession")
    public Map<String, Object> getRealmRoles(Authentication authentication) {

        JwtAuthenticationToken jwtAuthToken = (JwtAuthenticationToken) authentication;

        Jwt jwt = jwtAuthToken.getToken();

        Map<String, Object> realmAccess = jwt.getClaim("realm_access");

        return Map.of("realm_access", realmAccess);
    }

    @GetMapping("/myRoles")
    public Map<String, Object> getRoles(Authentication authentication) {

        JwtAuthenticationToken jwtAuthToken = (JwtAuthenticationToken) authentication;
        Jwt jwt = jwtAuthToken.getToken();

        Map<String, Object> realmAccess = jwt.getClaim("realm_access");


        Object roles = realmAccess.get("roles");

        return Map.of("roles", roles);
    }

    @GetMapping("/myTokenDetails")
    public Map<String, Object> getFullTokenDetails(Authentication authentication) {

        JwtAuthenticationToken jwtAuthToken = (JwtAuthenticationToken) authentication;
        Jwt jwt = jwtAuthToken.getToken();

        // Return all claims from the JWT
        return jwt.getClaims();
    }
    @GetMapping("/myAuthorities")
    public Map<String, Object> getAuthorities(Authentication authentication) {
        return Map.of("authorities", authentication.getAuthorities());
    }
    @GetMapping("/myAuthoritiesFull")
    public Map<String, Object> getFullAuthorities(Authentication authentication) {
        // Return all authorities as a list of strings
        var authorities = authentication.getAuthorities().stream()
                .map(ga -> ga.getAuthority()) // get the string like "ROLE_USER"
                .toList();

        return Map.of("authorities", authorities);
    }

}
