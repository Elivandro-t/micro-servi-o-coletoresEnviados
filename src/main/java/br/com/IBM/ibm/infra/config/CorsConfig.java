package br.com.IBM.ibm.infra.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public interface CorsConfig extends WebMvcConfigurer {
    void addCorsMapping(CorsRegistry corsRegistry);
}
