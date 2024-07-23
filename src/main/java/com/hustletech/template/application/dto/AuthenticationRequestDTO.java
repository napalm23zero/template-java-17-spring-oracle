package com.hustletech.template.application.dto;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {
    private String username;
    private String password;
}
