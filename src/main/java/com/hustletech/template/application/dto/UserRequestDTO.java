package com.hustletech.template.application.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

@Data
public class UserRequestDTO {
    @NotBlank(message = "Name cannot be empty.")
    @Length(min = 2, max = 100, message = "Name must be between 2 and 100 characters.")
    private String name;

    @NotBlank(message = "Email cannot be empty.")
    @Email(message = "Email must be a valid email address.")
    private String email;

    @NotNull(message = "Birthday cannot be null.")
    @Past(message = "Birthday must be in the past.")
    private LocalDate birthday;
}
