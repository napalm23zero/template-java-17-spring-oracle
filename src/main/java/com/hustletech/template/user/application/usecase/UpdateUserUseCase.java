package com.hustletech.template.user.application.usecase;

import org.springframework.stereotype.Component;

import com.hustletech.template.user.adapter.service.UserService;
import com.hustletech.template.user.application.dto.UserRequestDTO;
import com.hustletech.template.user.application.dto.UserResponseDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateUserUseCase {

    private final UserService userService;

    public UserResponseDTO execute(Long id, UserRequestDTO requestDTO) {
        return userService.update(id, requestDTO);
    }
}