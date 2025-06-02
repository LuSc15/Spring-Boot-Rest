package com.example.springapidemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Erlaubt alle Endpunkte
                        .allowedOrigins("http://localhost:3000","http://192.168.0.150:3000") // Erlaubt Zugriff von React
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Erlaubt HTTP-Methoden
                        .allowedHeaders("*"); // Erlaubt alle Header
            }
        };
    }
}