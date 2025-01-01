package com.uydev.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("CaseStudy application OpenApi")
                        .version("v1")
                        .description("CaseStudy Application Open Api documentation"))
                        .servers(List.of(new Server().url("http://localhost:8080")
                        .description("cloud server"), new Server().url("https://uydev.com.tr")
                        .description("creator web site")));
    }

}
