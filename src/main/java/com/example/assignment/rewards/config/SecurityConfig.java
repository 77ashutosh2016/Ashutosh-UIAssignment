package com.example.assignment.rewards.config;

import com.example.assignment.rewards.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig  {

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity (not recommended for production)
                .authorizeHttpRequests(authorize -> authorize
                        // Allow access to OpenAPI and Swagger UI endpoints
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/actuator/**").permitAll()

                        // Allow access to registration, login, and H2 Console
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/api/customers/register", "/api/customers/login","/swagger-ui.html").permitAll()

                        // Secure all other endpoints
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults()) // Enable form-based login with default configuration
                .httpBasic(withDefaults()) // Enable HTTP Basic authentication
                .headers(headers -> headers
                        .frameOptions(frameOptionsConfigurer -> frameOptionsConfigurer.disable()) // Disable frame options for H2 Console
                );

        return http.build();
    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
