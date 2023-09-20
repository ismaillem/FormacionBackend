package com.example.SpringProfilesApp;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@Getter
public class AppConfig
{
    @Value("${app.name}")
    private String name;

    @Value("${app.environment}")
    private String environment;

    @Value("${bd.url}")
    private String bd;
}