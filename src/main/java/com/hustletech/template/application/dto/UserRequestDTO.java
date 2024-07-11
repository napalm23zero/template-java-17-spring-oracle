package com.hustletech.template.application.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserRequestDTO {
    private String name;
    private String email;
    private LocalDateTime birthday;
}