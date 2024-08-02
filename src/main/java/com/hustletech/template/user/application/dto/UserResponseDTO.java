package com.hustletech.template.user.application.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String username;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
