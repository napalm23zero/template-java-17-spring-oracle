package com.hustletech.template.auth.adapter.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hustletech.template.auth.application.dto.AuthenticationRequestDTO;
import com.hustletech.template.shared.utils.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller responsible for handling authentication requests.
 * This class leverages Spring Security to authenticate a user and issue a JWT.
 */
@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AuthenticationController {

    private static final String JWT_TOKEN_KEY = "jwt";
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    /**
     * POST endpoint to authenticate a user and issue a JWT.
     * This method will authenticate the user based on the provided credentials and,
     * upon success,
     * issue a JWT which is then returned in a map with a "jwt" key.
     *
     * @param authenticationRequest DTO containing username and password
     * @return A map containing the JWT token
     * @throws AuthenticationException if authentication fails, the dark side is not
     *                                 forgiving
     */
    @PostMapping("/authenticate")
    public Map<String, String> createAuthenticationToken(@RequestBody AuthenticationRequestDTO authenticationRequest)
            throws AuthenticationException {
        log.debug("Initiating authentication attempt for user: {}", authenticationRequest.getUsername());

        try {
            Authentication authentication = authenticate(authenticationRequest);
            log.debug("User authenticated successfully: {}", authenticationRequest.getUsername());

            String jwt = generateJwtToken(authenticationRequest.getUsername());
            log.debug("JWT token crafted with Sith alchemy for user: {}", authenticationRequest.getUsername());

            SecurityContextHolder.getContext().setAuthentication(authentication);
            return createResponse(jwt);
        } catch (AuthenticationException e) {
            log.error("Failed to authenticate user: {}. The force wasn't strong with this one.",
                    authenticationRequest.getUsername(), e);
            throw e;
        }
    }

    /**
     * Authenticate with the provided request credentials using Spring Security's
     * mechanism.
     *
     * @param authenticationRequest DTO with username and password
     * @return a valid Authentication object on successful auth
     */
    private Authentication authenticate(AuthenticationRequestDTO authenticationRequest) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));
    }

    /**
     * Generates a JWT for a given username.
     *
     * @param username The username for which to generate the JWT
     * @return A JWT string
     */
    private String generateJwtToken(String username) {
        return jwtUtil.generateToken(username);
    }

    /**
     * Create a response map containing the JWT token.
     *
     * @param jwt The generated JWT token
     * @return A map with a single key-value pair where the key is 'jwt'
     */
    private Map<String, String> createResponse(String jwt) {
        return Collections.singletonMap(JWT_TOKEN_KEY, jwt);
    }
}
