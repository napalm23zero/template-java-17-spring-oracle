package com.hustletech.template.person.application.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hustletech.template.shared.validation.EmailProviderValidation;
import com.hustletech.template.shared.validation.HumanDateValidation;
import com.hustletech.template.shared.validation.NameValidation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PersonRequestDTO {
    @NameValidation
    @Schema(description = "Name of the person", example = "Peter Parker")
    private String name;

    @EmailProviderValidation
    @Schema(description = "Email of the person", example = "peter.parker@dailybugle.com")
    private String email;

    @HumanDateValidation
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Birthday of the person", example = "2001-08-10")
    private LocalDate birthday;
}
