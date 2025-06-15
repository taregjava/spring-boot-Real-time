package com.halfacode.spring_real_time_learning.mapper;


import com.halfacode.spring_real_time_learning.dto.UserDto;
import com.halfacode.spring_real_time_learning.entity.UserEntity;
import org.mapstruct.*;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")   // ✅ Explicitly map fields
    @Mapping(target = "email", source = "email") // ✅ Map email too
    UserEntity toEntity(UserDto dto, @Context OperationType op);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UserDto dto, @MappingTarget UserEntity entity, @Context OperationType op);

    @AfterMapping
    default void afterMapping(@MappingTarget UserEntity entity, @Context OperationType op) {
        if (op == OperationType.INSERT) {
            entity.setCreatedAt(LocalDateTime.now());
            entity.setDeleted(false);
        } else if (op == OperationType.UPDATE) {
            entity.setUpdatedAt(LocalDateTime.now());
        } else if (op == OperationType.DELETE) {
            entity.setDeleted(true);
        }
    }
}
