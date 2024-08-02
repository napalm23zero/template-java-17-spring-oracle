package com.hustletech.template.user.adapter.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hustletech.template.domain.entity.User;
import com.hustletech.template.shared.adapters.controller._GenericController;
import com.hustletech.template.user.adapter.service.UserService;
import com.hustletech.template.user.application.dto.UserFilterDTO;
import com.hustletech.template.user.application.dto.UserRequestDTO;
import com.hustletech.template.user.application.dto.UserResponseDTO;

/**
 * Controller for managing user-related operations.
 * Extends the generic controller to provide CRUD operations for User entities.
 * Enforces that all methods require the ADMIN role by default.
 */
@RestController
@RequestMapping("/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserController extends _GenericController<User, UserRequestDTO, UserResponseDTO, UserFilterDTO, Long> {

    /**
     * Constructs a UserController with the specified UserService.
     * Enforces that all methods in this controller require at least the ADMIN role.
     *
     * @param userService the service to handle user-related operations
     */
    public UserController(UserService userService) {
        super(userService, UserFilterDTO.class);
    }
}
