package com.eventstoday.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class EventstodayApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventstodayApplication.class, args);
    }

    @Bean
   public WebMvcConfigurer corsConfigurer() {return new WebMvcConfigurer() {
           @Override
          public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**").allowedOrigins(CorsConfiguration.ALL).allowedMethods(CorsConfiguration.ALL).allowedHeaders(CorsConfiguration.ALL);
}
    };
   }




}
