package com.halfacode.spring_real_time_learning;

import com.halfacode.spring_real_time_learning.model.PermissionEntity;
import com.halfacode.spring_real_time_learning.model.RoleEntity;
import com.halfacode.spring_real_time_learning.model.RoleEnum;
import com.halfacode.spring_real_time_learning.model.UserEntity;
import com.halfacode.spring_real_time_learning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringRealTimeLearningApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringRealTimeLearningApplication.class, args);
	}
	@Autowired
	UserRepository userRepository;
	@Override
	public void run(String... args) throws Exception {
		/* CREATE PERMISSIONS */
		PermissionEntity readComic = PermissionEntity.builder()
				.permissionName("READ_COMIC")
				.build();

		PermissionEntity readCharacter = PermissionEntity.builder()
				.permissionName("READ_CHARACTER")
				.build();

		PermissionEntity createUser = PermissionEntity.builder()
				.permissionName("CREATE_USER")
				.build();

		PermissionEntity invalidUser = PermissionEntity.builder()
				.permissionName("INVALID_USER")
				.build();

		/* CREATE ROLES */
		RoleEntity admin = RoleEntity.builder()
				.roleName(RoleEnum.ADMIN)
				.permissions(Set.of(readComic, readCharacter, createUser, invalidUser))
				.build();

		RoleEntity user = RoleEntity.builder()
				.roleName(RoleEnum.USER)
				.permissions(Set.of(readCharacter, readComic))
				.build();

		/* CREATE USERS */
		UserEntity userJuan = UserEntity.builder()
				.username("juan")
				.password("$2a$10$AwBc8cZfghF3qTfa9dei1uI8gVtWPRccli6//zPjQmydF3StGKLpC")
				.isEnabled(true)
				.accountNoLocked(true)
				.accountNoExpired(true)
				.credentialNoExpired(true)
				.roles(Set.of(admin))
				.build();

		UserEntity userSantiago = UserEntity.builder()
				.username("tareg")
				.password("$2a$10$AwBc8cZfghF3qTfa9dei1uI8gVtWPRccli6//zPjQmydF3StGKLpC")
				.isEnabled(true)
				.accountNoLocked(true)
				.accountNoExpired(true)
				.credentialNoExpired(true)
				.roles(Set.of(user))
				.build();

		userRepository.saveAll(List.of(userSantiago, userJuan));

	}


}
