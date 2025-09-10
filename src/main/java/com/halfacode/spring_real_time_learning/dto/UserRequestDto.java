package com.halfacode.spring_real_time_learning.dto;
import lombok.Data;
import java.util.List;

@Data
public class UserRequestDto {
    private String email;
    private String firstName;
    private String lastName;
    private String title;
    private String defaultOrganizationId;
    private List<String> roleOrganizationIds;
}
