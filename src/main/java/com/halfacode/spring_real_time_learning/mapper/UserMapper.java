package com.halfacode.spring_real_time_learning.mapper;

import com.halfacode.spring_real_time_learning.dto.UserDto;
import com.halfacode.spring_real_time_learning.entity.Role;
import com.halfacode.spring_real_time_learning.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Entity -> DTO
    @Mapping(target = "fullName", expression = "java(user.getFirstName() + \" \" + user.getLastName())")
    @Mapping(source = "department.name", target = "departmentName")
    @Mapping(source = "roles", target = "roles", qualifiedByName = "mapRoles")
    UserDto toDto(User user);

    // DTO -> Entity
  /*  @Mapping(target = "id", ignore = true)
    @Mapping(target = "firstName", source = "fullName", qualifiedByName = "extractFirstName")
    @Mapping(target = "lastName", source = "fullName", qualifiedByName = "extractLastName")
    // Assume Department and Roles are set in service after mapping
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toEntity(UserDto dto);*/

    // Custom methods
    @Named("extractFirstName")
    default String extractFirstName(String fullName) {
        if(fullName != null && fullName.contains(" ")) return fullName.split(" ")[0];
        return fullName;
    }

    @Named("extractLastName")
    default String extractLastName(String fullName) {
        if(fullName != null && fullName.contains(" ")) return fullName.substring(fullName.indexOf(" ") + 1);
        return "";
    }

    @Named("mapRoles")
    default Set<String> mapRoles(Set<Role> roles) {
        if (roles == null) return null;
        return roles.stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet());
    }
}
