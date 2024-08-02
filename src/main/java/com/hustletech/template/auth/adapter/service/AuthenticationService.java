package com.hustletech.template.auth.adapter.service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hustletech.template.domain.entity.Role;
import com.hustletech.template.domain.entity.User;
import com.hustletech.template.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service for managing user authentication.
 * Provides methods to create users and load user details from the database.
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Registers a new user with specified credentials and roles.
     * This method will encode the password and store the user in the database.
     *
     * @param username the desired username
     * @param password the raw password to be encoded and stored
     * @param roles    a set of roles to be assigned to the new user
     */
    public void createUser(String username, String password, Set<Role> roles) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // Ensure password is securely stored
        user.setRoles(roles);
        userRepository.save(user);
        log.debug("Created new user with username: {}", username); // Log the success of user creation
    }

    /**
     * Loads user details by their username.
     * This method is called during the authentication process to fetch user
     * details.
     *
     * @param username the username to look up
     * @return UserDetails populated with user's data
     * @throws UsernameNotFoundException if no user with the given username is found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Attempting to load user details for username: {}", username);
        return userRepository.findByUsername(username)
                .map(user -> {
                    log.debug("User found with username: {}", username); // Log if user is successfully found
                    return new org.springframework.security.core.userdetails.User(
                            user.getUsername(),
                            user.getPassword(),
                            mapRolesToAuthorities(user.getRoles()));
                })
                .orElseThrow(() -> {
                    log.error("Failed to find user with username: {}", username); // Log an error if user is not found
                    return new UsernameNotFoundException("User not found with username: " + username);
                });
    }

    /**
     * Maps roles to Spring Security GrantedAuthority.
     *
     * @param roles the roles to map
     * @return a collection of GrantedAuthority
     */
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());
    }
}
