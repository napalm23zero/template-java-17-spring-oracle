package com.hustletech.template.user.application.usecase;

import org.springframework.stereotype.Component;

import com.hustletech.template.user.adapter.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteUserUseCase {

    private final UserService userService;

    public void execute(Long id) {
        userService.delete(id);
    }
}