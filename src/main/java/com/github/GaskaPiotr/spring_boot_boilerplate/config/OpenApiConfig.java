package com.github.GaskaPiotr.spring_boot_boilerplate.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Boilerplate Spring Boot API Project")
                        .version("1.0.0")
                        .description("This is the backend boilerplate API for general purpose. It manages users, registering and logging"));
    }
}
