package com.hustletech.template.application.usecase.user;

import org.springframework.stereotype.Component;

import com.hustletech.template.adapters.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteUserUseCase {

    private final UserService userService;

    public void execute(Long id) {
        userService.delete(id);
    }
}