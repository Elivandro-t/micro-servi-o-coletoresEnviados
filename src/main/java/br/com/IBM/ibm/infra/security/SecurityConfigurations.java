package br.com.IBM.ibm.infra.security;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    private FilterValidator filterValidator;
    @Bean
    public  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http.csrf(e->e.disable())
                 .cors(e->e.disable())
                .sessionManagement(e->e.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        e->e.requestMatchers(HttpMethod.POST,"/service/api/registrar")
                        .permitAll()
                                .requestMatchers(HttpMethod.GET,"/service/api/{id}/entregar").permitAll()
                                .requestMatchers(HttpMethod.POST,"/service/api/atualizar").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(e->e.disable())
                 .addFilterBefore(filterValidator, UsernamePasswordAuthenticationFilter.class);
         return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
