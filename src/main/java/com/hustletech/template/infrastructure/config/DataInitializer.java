package com.hustletech.template.infrastructure.config;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.hustletech.template.auth.adapter.service.AuthenticationService;
import com.hustletech.template.domain.entity.Endpoint;
import com.hustletech.template.domain.entity.Role;
import com.hustletech.template.domain.entity.RoleEndpoint;
import com.hustletech.template.domain.repository.EndpointRepository;
import com.hustletech.template.domain.repository.RoleEndpointRepository;
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
    private final EndpointRepository endpointRepository;
    private final RoleEndpointRepository roleEndpointRepository;
    private final AuthenticationService userService;
    private final RequestMappingHandlerMapping requestMappingHandlerMapping;

    @SuppressWarnings("null")
    @Bean
    public ApplicationRunner initializer() {
        return args -> {
            // Save all endpoints to the database
            Set<String> allEndpoints = requestMappingHandlerMapping.getHandlerMethods().keySet().stream()
                    .map(info -> info.getPatternsCondition() != null ? info.getPatternsCondition().getPatterns() : null)
                    .filter(patterns -> patterns != null && !patterns.isEmpty())
                    .flatMap(Set::stream)
                    .collect(Collectors.toSet());

            for (String url : allEndpoints) {
                if (!endpointRepository.existsByUrl(url)) {
                    endpointRepository.save(new Endpoint(url));
                }
            }

            if (roleRepository.count() == 0) {
                log.info("Initializing database with default roles");

                Role adminRole = roleRepository.save(new Role("ADMIN"));
                Role userRole = roleRepository.save(new Role("USER"));
                log.info("Created roles: ADMIN and USER");

                List<Endpoint> endpoints = endpointRepository.findAll();
                for (Endpoint endpoint : endpoints) {
                    if (!roleEndpointRepository.existsByRoleAndEndpoint(adminRole, endpoint)) {
                        roleEndpointRepository.save(new RoleEndpoint(adminRole, endpoint));
                    }
                }

                if (userRepository.count() == 0) {
                    userService.createUser("admin", "admin", Set.of(adminRole));
                    userService.createUser("user", "user", Set.of(userRole));
                    log.info("Created default admin and user accounts");
                }
            }
        };
    }
}
