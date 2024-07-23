package com.hustletech.template.adapters.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hustletech.template.domain.entity.User;

public class CustomUserDetails implements UserDetails {

    private final User user;

    /**
     * Constructs a new CustomUserDetails instance.
     *
     * @param user the User entity
     */
    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    // Get the authorities granted to the user
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    // Get the user's password
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    // Get the user's username
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    // Check if the user's account is non-expired
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    // Check if the user's account is non-locked
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    // Check if the user's credentials are non-expired
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    // Check if the user is enabled
    public boolean isEnabled() {
        return true;
    }
}
