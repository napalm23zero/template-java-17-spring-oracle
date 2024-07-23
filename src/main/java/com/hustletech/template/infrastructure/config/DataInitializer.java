package com.hustletech.template.infrastructure.config;

import java.util.Collections;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hustletech.template.adapters.service.UserService;
import com.hustletech.template.domain.entity.Role;
import com.hustletech.template.domain.repository.RoleRepository;
import com.hustletech.template.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserService userService;

    /**
     * Initializes the database with default roles and admin user if none exist.
     *
     * @return an ApplicationRunner to initialize data
     */
    @Bean
    public ApplicationRunner initializer() {
        return args -> {
            if (userRepository.count() == 0 && roleRepository.count() == 0) {
                log.info("Initializing database with default roles and admin user");

                Role adminRole = roleRepository.save(new Role("ROLE_ADMIN"));
                log.info("Created admin role: {}", adminRole);

                userService.createUser("admin", "admin", Collections.singleton(adminRole));
                log.info("Created default admin user");
            }
        };
    }
}
