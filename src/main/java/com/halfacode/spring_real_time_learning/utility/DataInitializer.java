package com.halfacode.spring_real_time_learning.utility;

import com.halfacode.spring_real_time_learning.entity.*;
import com.halfacode.spring_real_time_learning.repo.PermissionRepository;
import com.halfacode.spring_real_time_learning.repo.RoleRepository;
import com.halfacode.spring_real_time_learning.repo.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        // Ensure roles exist first — skip if already initialized
        saveRoleIfMissing(RoleEnum.ADMIN);
        saveRoleIfMissing(RoleEnum.USER);
        saveRoleIfMissing(RoleEnum.DEVELOPER);

        // Fetch managed RoleEntity instances from DB
        RoleEntity adminRole = roleRepository.findByRoleEnum(RoleEnum.ADMIN)
                .orElseThrow(() -> new RuntimeException("Role ADMIN not found"));
        RoleEntity userRole = roleRepository.findByRoleEnum(RoleEnum.USER)
                .orElseThrow(() -> new RuntimeException("Role USER not found"));
        RoleEntity devRole = roleRepository.findByRoleEnum(RoleEnum.DEVELOPER)
                .orElseThrow(() -> new RuntimeException("Role DEVELOPER not found"));

        // Save users with properly fetched roles
        saveUser("admin_user", "hashedpassword1", adminRole);
        saveUser("normal_user", "hashedpassword2", userRole);
        saveUser("dev_user", "hashedpassword3", devRole);
        saveUser("tareg", "123456789", userRole);
    }

    private void saveUser(String username, String rawPassword, RoleEntity role) {
        if (userRepository.findByUsername(username).isPresent()) return;

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(role);
        user.setEnabled(true);
        user.setAccountNoExpired(true);
        user.setAccountNoLocked(true);
        user.setCredentialNoExpired(true);
        userRepository.save(user);
    }

    private void saveRoleIfMissing(RoleEnum roleEnum) {
        roleRepository.findByRoleEnum(roleEnum)
                .orElseGet(() -> roleRepository.save(RoleEntity.builder()
                        .roleEnum(roleEnum)
                        .build()));
    }

    private PermissionEntity savePermission(String name) {
        return permissionRepository.findByName(name)
                .orElseGet(() -> permissionRepository.save(PermissionEntity.builder().name(name).build()));
    }
}
