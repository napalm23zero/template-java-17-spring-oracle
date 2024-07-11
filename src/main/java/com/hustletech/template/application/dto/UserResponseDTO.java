package com.hustletech.template.application.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime birthday;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}