package com.sysmap.laersonjr.socialnetwork.core.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    private final List<String> allowedOrigins = List.of("*");
    private final List<String> allowedMethods = List.of("GET", "POST", "PUT", "DELETE");
    private final List<String> allowedHeaders = List.of("Content-Type", "Authorization");

    private final List<String> noTokenRoutes = List.of("/swagger-ui.html", "/login", "/api/v1/users/create");

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(String.valueOf(allowedOrigins))
                .allowedMethods(allowedMethods.toArray(String[]::new))
                .allowedHeaders(allowedHeaders.toArray(String[]::new))
                .exposedHeaders("Authorization")
                .allowCredentials(true)
                .maxAge(3600);

        registry.addMapping(noTokenRoutes.toString())
                .allowedOrigins(String.valueOf(allowedOrigins))
                .allowedMethods(allowedMethods.toArray(String[]::new))
                .allowedHeaders(allowedHeaders.toArray(String[]::new))
                .exposedHeaders("Authorization")
                .allowCredentials(true)
                .maxAge(3600);
    }
}

