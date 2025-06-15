package com.halfacode.spring_real_time_learning.controller;

import com.halfacode.spring_real_time_learning.dto.UserDto;
import com.halfacode.spring_real_time_learning.entity.UserEntity;
import com.halfacode.spring_real_time_learning.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public UserEntity create(@RequestBody UserDto dto) {
        return service.createUser(dto);
    }

    @PutMapping("/{id}")
    public UserEntity update(@PathVariable Long id, @RequestBody UserDto dto) {
        return service.updateUser(id, dto);
    }

    @PutMapping("/delete/{id}")
    public void softDelete(@PathVariable Long id) {
        service.softDeleteUser(id);
    }
}
