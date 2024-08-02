package com.hustletech.template.user.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserFilterDTO {
    @Schema(description = "User's username", example = "peter.parker")
    private String username;
}
