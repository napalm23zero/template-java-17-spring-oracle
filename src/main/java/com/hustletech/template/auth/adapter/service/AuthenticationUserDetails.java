package com.hustletech.template.auth.adapter.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hustletech.template.domain.entity.User;

/**
 * Custom implementation of UserDetails that handles the authentication details
 * of a user.
 * This class encapsulates the user's authentication-related properties and
 * authorities,
 * providing a clean API for Spring Security to use during authentication and
 * authorization processes.
 */
public class AuthenticationUserDetails implements UserDetails {

    private final User user;

    /**
     * Constructor that initializes the AuthenticationUserDetails with a specific
     * user entity.
     *
     * @param user The User entity containing credentials and roles
     */
    public AuthenticationUserDetails(User user) {
        this.user = user;
    }

    /**
     * Fetches the authorities (permissions) for the user, converting them from
     * roles to Spring Security's
     * GrantedAuthority objects.
     *
     * @return a collection of GrantedAuthority objects representing user's roles
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());
    }

    /**
     * Returns the password used for authenticating the user.
     *
     * @return the user's hashed password
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Returns the username used for authenticating the user.
     *
     * @return the user's username
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * Indicates whether the user's account is still valid (i.e., not expired).
     *
     * @return true if the account is valid, false if it's expired
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Checks if the account is not locked.
     *
     * @return true if account is not locked, false otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) are still valid.
     *
     * @return true if the credentials haven't expired, false otherwise
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Checks if the user's account is enabled, allowing login.
     *
     * @return true if the account is enabled, false otherwise
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
