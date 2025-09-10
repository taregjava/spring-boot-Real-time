package com.halfacode.spring_real_time_learning.controller;

import com.halfacode.spring_real_time_learning.dto.UserRequestDto;
import com.halfacode.spring_real_time_learning.entity.RoleOrganization;
import com.halfacode.spring_real_time_learning.entity.User;
import com.halfacode.spring_real_time_learning.repository.RoleOrganizationRepository;
import com.halfacode.spring_real_time_learning.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserRepository userRepo;
    private final RoleOrganizationRepository roleOrgRepo;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserRepository userRepo, RoleOrganizationRepository roleOrgRepo) {
        this.userRepo = userRepo;
        this.roleOrgRepo = roleOrgRepo;
    }

   /* @PostMapping("/{userId}")
    public User create(@PathVariable String userId, @RequestBody User user) {
        user.setUserId(userId);

        // Validate and fetch RoleOrganization entities
        List<String> roleIds = user.getRoleOrganizations()
                .stream()
                .map(RoleOrganization::getRoleOrganizationId)
                .toList();

        List<RoleOrganization> roles = roleOrgRepo.findByRoleOrganizationIds(roleIds);

        if (roles.size() != roleIds.size()) {
            throw new RuntimeException("Some roleOrganizationIds do not exist in DB");
        }

        user.setRoleOrganizations(roles);

        return userRepo.save(user);
    }*/

  /*  @PostMapping("/{userId}")
    public User create(@PathVariable String userId, @RequestBody User user) {
        user.setUserId(userId);

        // Ensure roleOrganizations is not null
        List<RoleOrganization> roleList;
        if (user.getRoleOrganizations() != null && !user.getRoleOrganizations().isEmpty()) {
            List<String> roleIds = user.getRoleOrganizations()
                    .stream()
                    .map(RoleOrganization::getRoleOrganizationId)
                    .toList();

            roleList = roleOrgRepo.findByRoleOrganizationIds(roleIds);

            if (roleList.size() != roleIds.size()) {
                throw new RuntimeException("Some roleOrganizationIds do not exist in DB");
            }
        } else {
            throw new RuntimeException("Some roleOrganizationIds do not exist in DB");
           // roleList = List.of(); // empty list if none provided
        }

        user.setRoleOrganizations(roleList);

        return userRepo.save(user);
    }
*/


    @PostMapping("/{userId}")
    public User create(@PathVariable String userId, @RequestBody UserRequestDto dto) {
       /* User user = userRepo.findById(userId).orElseGet(() -> {
            log.info("User with ID {} not found, creating a new one", userId);
            return new User();
        });*/
       // User user = userRepo.findById(userId).orElse(new User());

        User user = userRepo.findById(userId).orElseGet(() ->{
            log.info("User with ID {} not found, creating a new one", userId);
            return new User();
        });
        if (user.getUserId() != null) {
            log.info("Updating existing user with ID {}", userId);
        }
        userRepo.findById(userId).orElseThrow(RuntimeException::new);
        user.setUserId(userId);
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setTitle(dto.getTitle());
        user.setDefaultOrganizationId(dto.getDefaultOrganizationId());

        // Validate role IDsin spring boot in method signature why now aday we used httpServletResponse ?///
        List<RoleOrganization> roles = roleOrgRepo.findAllById(dto.getRoleOrganizationIds());
        if (roles.size() != dto.getRoleOrganizationIds().size()) {
            throw new RuntimeException("Some roleOrganizationIds do not exist in DB");
        }

        user.setRoleOrganizations(roles);

        return userRepo.save(user);
    }


    /*  @PostMapping("/{userId}")
    public User create(@PathVariable String userId, @RequestBody User user) {
        user.setUserId(userId);

        // Ensure roleOrganizations is not null
        List<RoleOrganization> roleIds = user.getRoleOrganizations();
        List<RoleOrganization> roleList = roleOrgRepo.findByRoleOrganizationIds(roleIds);

        if (roleList.size() != roleIds.size()) {
            throw new RuntimeException("Some roleOrganizationIds do not exist in DB");
        }

        user.setRoleOrganizations(roleList);
        userRepo.save(user);

    }
*/
    @GetMapping
    public List<User> getAll() {
        return userRepo.findAll();
    }
}

