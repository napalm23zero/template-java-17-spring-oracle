package com.hustletech.template.auth.application.dto;

import lombok.Data;

/**
 * Data Transfer Object for authentication requests.
 * Contains user credentials necessary for authentication.
 */
@Data
public class AuthenticationRequestDTO {
    private String username;
    private String password;
}
