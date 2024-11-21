package com.eScheduler.eScheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Dozvoli CORS za sve rute sa svih domena
        registry.addMapping("/**")  // Dodaj ovo da dozvoliš CORS za sve endpoint-e
                .allowedOrigins("http://localhost:4200")  // Dozvoli samo za Angular aplikaciju
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Definiši metode
                .allowedHeaders("*")  // Dozvoli sve zaglavlja
                .allowCredentials(true);  // Dozvoli korišćenje kolačića ili drugih kredencijala
    }
}