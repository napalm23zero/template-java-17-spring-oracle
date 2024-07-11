package com.hustletech.template.application.usecase.user;

import org.springframework.stereotype.Component;

import com.hustletech.template.adapters.service.UserService;
import com.hustletech.template.application.dto.UserRequestDTO;
import com.hustletech.template.application.dto.UserResponseDTO;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UpdateUserUseCase {

    private final UserService userService;

    public UserResponseDTO execute(Long id, UserRequestDTO requestDTO) {
        return userService.update(id, requestDTO);
    }
}