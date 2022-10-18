package com.example.tacocloudrest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        return http
//                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/ingredients").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/ingredients/**").hasRole("ADMIN")
//                .and().build();

        return http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/ingredients").hasAuthority("SCOPE_writeIngredients")
                .antMatchers(HttpMethod.DELETE, "/api/ingredients").hasAuthority("SCOPE_deleteIngredients")
                .and().oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .build();
    }
}
