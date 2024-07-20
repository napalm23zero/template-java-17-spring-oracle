package com.hustletech.template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Template API")
                        .version("1.0")
                        .summary("Template API")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .email("rodrigo.dantas@hustletech.dev")
                                .name("Rodrigo Dantas")
                                .url("https://github.com/napalm23zero"))
                        .description("API documentation for the Template Java 17 Spring Oracle project"));
    }
}
