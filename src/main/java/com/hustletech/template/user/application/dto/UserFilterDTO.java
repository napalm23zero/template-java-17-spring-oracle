package com.hustletech.template.user.application.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserFilterDTO {
    @Schema(description = "Name of the user", example = "Peter Parker")
    private String name;

    @Schema(description = "Email of the user", example = "peter.parker@dailybugle.com")
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Birthday of the user", example = "2001-08-10")
    private LocalDate birthday;
}
