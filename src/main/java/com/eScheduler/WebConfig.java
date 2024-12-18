package com.eScheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Dozvoli CORS za sve rute sa svih domena
        registry.addMapping("/api/**")  //CORS za sve endpoint-e
                .allowedOrigins("http://localhost:4200")  // samo Moja Angular aplikacija
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")  // Dozvoli sve zaglavlja
                .allowCredentials(true);
    }
}