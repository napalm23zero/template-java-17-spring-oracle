package com.hustletech.template.person.application.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PersonFilterDTO {
    @Schema(description = "Name of the person", example = "Peter Parker")
    private String name;

    @Schema(description = "Email of the person", example = "peter.parker@dailybugle.com")
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Birthday of the person", example = "2001-08-10")
    private LocalDate birthday;
}
