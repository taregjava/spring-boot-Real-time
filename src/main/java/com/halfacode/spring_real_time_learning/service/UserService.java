package com.halfacode.spring_real_time_learning.service;

import com.halfacode.spring_real_time_learning.dto.UserDto;
import com.halfacode.spring_real_time_learning.entity.UserEntity;
import com.halfacode.spring_real_time_learning.mapper.OperationType;
import com.halfacode.spring_real_time_learning.mapper.UserMapper;
import com.halfacode.spring_real_time_learning.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;
    private final UserMapper mapper;

    public UserService(UserRepository repo, UserMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public UserEntity createUser(UserDto dto) {
        UserEntity entity = mapper.toEntity(dto, OperationType.INSERT);
        return repo.save(entity);
    }

    public UserEntity updateUser(Long id, UserDto dto) {
        UserEntity entity = repo.findById(id).orElseThrow();
        mapper.updateEntityFromDto(dto, entity, OperationType.UPDATE);
        return repo.save(entity);
    }

    public void softDeleteUser(Long id) {
        UserEntity entity = repo.findById(id).orElseThrow();
        mapper.updateEntityFromDto(new UserDto(), entity, OperationType.DELETE);
        repo.save(entity);
    }
}
