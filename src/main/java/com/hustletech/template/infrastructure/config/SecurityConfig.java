package com.hustletech.template.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hustletech.template.auth.adapter.service.AuthenticationService;
import com.hustletech.template.shared.utils.JwtRequestFilterUtil;
import com.hustletech.template.shared.utils.JwtUtil;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final @Lazy AuthenticationService authenticationService;
    private final CustomAuthorizationManager customAuthorizationManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtRequestFilterUtil jwtRequestFilter)
            throws Exception {
        jwtRequestFilter.setJwtUtil(jwtUtil);
        jwtRequestFilter.setAuthenticationService(authenticationService);

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/authenticate/**").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers("/**").hasRole("ADMIN")
                        .anyRequest().access(customAuthorizationManager))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
