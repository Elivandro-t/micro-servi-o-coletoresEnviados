package br.com.IBM.ibm.infra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfigs implements CorsConfig {
    @Override
    public  void  addCorsMapping(CorsRegistry corsRegistry){
        corsRegistry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("POST","GET","PUT","DELETE","PATCH","OPTIONS","HEAD","TRACE", "CONNECT");
    }
}
