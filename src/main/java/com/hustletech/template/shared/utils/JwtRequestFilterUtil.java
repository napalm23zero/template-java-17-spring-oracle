package com.hustletech.template.shared.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hustletech.template.auth.adapter.service.AuthenticationService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * JWT request filter that checks for valid JWT in the authorization header of
 * each HTTP request.
 * It is executed once per request and is responsible for setting the security
 * context based on JWT validation.
 */
@Component
@Slf4j
public class JwtRequestFilterUtil extends OncePerRequestFilter {

    private JwtUtil jwtUtil;
    private AuthenticationService authenticationService;

    private static final List<String> EXCLUDED_PATHS = Arrays.asList(
            "/api/authenticate",
            "/v3/api-docs",
            "/swagger-ui",
            "/swagger-ui.html");

    /**
     * Default constructor for component initialization.
     */
    public JwtRequestFilterUtil() {
    }

    /**
     * Sets the JWT utility class used for extracting and validating JWTs.
     * 
     * @param jwtUtil the JwtUtil instance
     */
    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * Sets the authentication service used for user detail loading.
     * 
     * @param authenticationService the AuthenticationService instance
     */
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Internal filter method to process the incoming request and validate JWTs.
     * Excludes specific paths from JWT requirement.
     * 
     * @param request  the incoming HTTP request
     * @param response the outgoing HTTP response
     * @param chain    the filter chain
     * @throws ServletException, IOException if an error occurs processing the
     *                           filter chain
     */
    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        log.info("Processing request URI: {}", requestURI);

        if (EXCLUDED_PATHS.stream().anyMatch(requestURI::startsWith)) {
            log.info("Request URI {} is excluded from JWT processing", requestURI);
            chain.doFilter(request, response);
            return;
        }

        final String requestTokenHeader = request.getHeader("Authorization");
        log.info("Authorization header: {}", requestTokenHeader);

        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(jwtToken);
                log.info("Extracted username from JWT: {}", username);
            } catch (IllegalArgumentException e) {
                log.error("Unable to get JWT Token", e);
            } catch (ExpiredJwtException e) {
                log.warn("JWT Token has expired", e);
            } catch (MalformedJwtException e) {
                log.error("Invalid JWT Token", e);
            }
        } else {
            log.warn("JWT Token does not begin with Bearer String");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            log.info("Security context is null, validating token for user: {}", username);
            UserDetails userDetails = this.authenticationService.loadUserByUsername(username);

            if (Boolean.TRUE.equals(jwtUtil.validateToken(jwtToken, userDetails.getUsername()))) {
                log.info("JWT Token is valid, setting security context for user: {}", username);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}