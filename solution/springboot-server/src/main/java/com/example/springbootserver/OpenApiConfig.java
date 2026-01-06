package com.example.springbootserver;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Anime Database API")
                        .version("1.0.0")
                        .description("RESTful API for accessing anime, characters, and voice actors/staff information for java-springboot server (TWEB 2025-2026 project). This API provides endpoints to search and retrieve detailed information about anime series, characters, and the people involved in anime production.")
                        .contact(new Contact()
                                .name("TWEB Group 1")
                                .email("tu@email.com")))
                .servers(List.of(
                        new Server().url("http://localhost:8082").description("Local Development Server")
                ));
    }
}
