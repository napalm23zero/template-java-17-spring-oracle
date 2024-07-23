package com.hustletech.template.adapters.service;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hustletech.template.domain.entity.Role;
import com.hustletech.template.domain.entity.User;
import com.hustletech.template.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Creates a new user with the specified username, password, and roles.
     *
     * @param username the username of the new user
     * @param password the password of the new user
     * @param roles    the roles assigned to the new user
     */
    public void createUser(String username, String password, Set<Role> roles) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roles);
        userRepository.save(user);
    }
}
