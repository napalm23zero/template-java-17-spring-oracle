package com.hustletech.template.user.adapter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hustletech.template.adapters.controller._GenericController;
import com.hustletech.template.domain.entity.User;
import com.hustletech.template.user.application.dto.UserFilterDTO;
import com.hustletech.template.user.application.dto.UserRequestDTO;
import com.hustletech.template.user.application.dto.UserResponseDTO;
import com.hustletech.template.user.application.usecase.CreateUserUseCase;
import com.hustletech.template.user.application.usecase.DeleteUserUseCase;
import com.hustletech.template.user.application.usecase.FindUserUseCase;
import com.hustletech.template.user.application.usecase.GetUserUseCase;
import com.hustletech.template.user.application.usecase.UpdateUserUseCase;

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
