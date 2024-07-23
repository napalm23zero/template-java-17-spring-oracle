package com.hustletech.template.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearerAuth",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("Template API")
                        .version("1.0")
                        .summary("Template API")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .email("rodrigo.dantas@hustletech.dev")
                                .name("Rodrigo Dantas")
                                .url("https://github.com/napalm23zero"))
                        .description("API documentation for the Template Java 17 Spring Oracle project"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }
}
