package com.halfacode.spring_real_time_learning.controller;

import com.halfacode.spring_real_time_learning.entity.RoleOrganization;
import com.halfacode.spring_real_time_learning.repository.RoleOrganizationRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/role-organizations")
public class RoleOrganizationController {

    private final RoleOrganizationRepository repo;

    public RoleOrganizationController(RoleOrganizationRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public RoleOrganization create(@RequestBody RoleOrganization roleOrg) {
        return repo.save(roleOrg);
    }

    @GetMapping
    public List<RoleOrganization> getAll() {
        return repo.findAll();
    }
}

