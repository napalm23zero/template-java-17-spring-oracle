package com.hustletech.template.user.application.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.hustletech.template.user.adapter.service.UserService;
import com.hustletech.template.user.application.dto.UserFilterDTO;
import com.hustletech.template.user.application.dto.UserResponseDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindUserUseCase {

    private final UserService userService;

    public Page<UserResponseDTO> execute(UserFilterDTO filter, Pageable pageable) {
        return userService.findAll(filter, pageable);
    }
}
