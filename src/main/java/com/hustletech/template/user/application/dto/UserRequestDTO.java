package com.hustletech.template.user.application.dto;

import com.hustletech.template.shared.validation.PasswordValidation;
import com.hustletech.template.shared.validation.UserNameValidation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserRequestDTO {
    @UserNameValidation
    @Schema(description = "User's username", example = "peter.parker")
    private String username;

    @PasswordValidation
    @Schema(description = "Password of the user", example = "*********")
    private String password;
}
