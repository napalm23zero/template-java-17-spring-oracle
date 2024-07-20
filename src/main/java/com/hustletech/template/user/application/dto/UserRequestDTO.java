package com.hustletech.template.user.application.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hustletech.template.shared.validation.EmailProviderValidation;
import com.hustletech.template.shared.validation.HumanDateValidation;
import com.hustletech.template.shared.validation.NameValidation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserRequestDTO {
    @NameValidation
    @Schema(description = "Name of the user", example = "Peter Parker")
    private String name;

    @EmailProviderValidation
    @Schema(description = "Email of the user", example = "peter.parker@dailybugle.com")
    private String email;

    @HumanDateValidation
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Birthday of the user", example = "2001-08-10")
    private LocalDate birthday;
}
