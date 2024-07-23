package com.hustletech.template.adapters.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hustletech.template.application.dto.AuthenticationRequestDTO;
import com.hustletech.template.shared.utils.JwtUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    /**
     * Authenticates the user and generates a JWT token.
     *
     * @param authenticationRequest the authentication request containing username
     *                              and password
     * @return a map containing the JWT token
     * @throws AuthenticationException if authentication fails
     */
    @PostMapping("/authenticate")
    public Map<String, String> createAuthenticationToken(@RequestBody AuthenticationRequestDTO authenticationRequest)
            throws AuthenticationException {
        log.debug("Attempting to authenticate user: {}", authenticationRequest.getUsername());

        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()));
            log.debug("Authentication successful for user: {}", authenticationRequest.getUsername());

            // Generate JWT token
            final String jwt = jwtUtil.generateToken(authenticationRequest.getUsername());
            Map<String, String> response = new HashMap<>();
            response.put("jwt", jwt);
            log.debug("JWT token generated and returned for user: {}", authenticationRequest.getUsername());

            return response;
        } catch (AuthenticationException e) {
            log.error("Authentication failed for user: {}", authenticationRequest.getUsername(), e);
            throw e;
        }
    }
}
