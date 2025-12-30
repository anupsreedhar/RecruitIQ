package com.zelexon.recruitiq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("dev")
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/h2-console/**", "/**").permitAll()
                .anyRequest().permitAll()
            .and()
            .headers().frameOptions().disable(); // for H2 console
        return http.build();
    }
}
