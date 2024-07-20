package com.hustletech.template.user.application.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UserFilterDTO {
    private String name;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
}
