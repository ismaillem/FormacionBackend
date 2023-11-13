package com.example.block11uploaddownloadfiles;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPI30Configuration {

    public OpenAPI openApi() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("API")
                                .description("API")
                                .version("v1.0")
                                .contact(null)
                                .termsOfService("TOC")
                                .license(new License().name("License").url("#")));
    }

    @Bean
    public GroupedOpenApi security() {
        return GroupedOpenApi.builder()
                .group("Security")
                .packagesToScan("com.project.autostocknuvex.security")
                .build();
    }
}