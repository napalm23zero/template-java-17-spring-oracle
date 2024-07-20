package com.hustletech.template.adapters.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hustletech.template.application.dto.UserFilterDTO;
import com.hustletech.template.application.dto.UserRequestDTO;
import com.hustletech.template.application.dto.UserResponseDTO;
import com.hustletech.template.application.usecase.user.CreateUserUseCase;
import com.hustletech.template.application.usecase.user.DeleteUserUseCase;
import com.hustletech.template.application.usecase.user.FindUserUseCase;
import com.hustletech.template.application.usecase.user.GetUserUseCase;
import com.hustletech.template.application.usecase.user.UpdateUserUseCase;
import com.hustletech.template.domain.entity.User;

@RestController
@RequestMapping("/users")
public class UserController extends _GenericController<User, UserRequestDTO, UserResponseDTO, UserFilterDTO, Long> {

    public UserController(CreateUserUseCase createUserUseCase,
            UpdateUserUseCase updateUserUseCase,
            DeleteUserUseCase deleteUserUseCase,
            GetUserUseCase getUserUseCase,
            FindUserUseCase findUserUseCase) {
        super(
                createUserUseCase::execute,
                updateUserUseCase::execute,
                deleteUserUseCase::execute,
                getUserUseCase::execute,
                findUserUseCase::execute);
    }
}
