package com.application.employee.service.config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.application.employee.service.user.Permission.*;
import static com.application.employee.service.user.Role.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests()
                .requestMatchers("/auth/**")
                .permitAll()
               .requestMatchers("/employees/**","/orders/**").hasAnyRole(ADMIN.name(),EMPLOYEE.name())
                .requestMatchers(GET,"/employees/**","/orders/**","/trackings/**").hasAnyAuthority(ADMIN_READ.name(),EMPLOYEE_READ.name())
                .requestMatchers(POST,"/employees/**","/orders/**","/trackings/**").hasAnyAuthority(ADMIN_CREATE.name(),EMPLOYEE_CREATE.name())
                .requestMatchers(PUT,"/employees/**","/orders/**","/trackings/**").hasAnyAuthority(ADMIN_UPDATE.name(),EMPLOYEE_UPDATE.name())
                .requestMatchers(DELETE,"/employees/**","/orders/**","/trackings/**").hasAnyAuthority(ADMIN_DELETE.name(),EMPLOYEE_DELETE.name())
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
