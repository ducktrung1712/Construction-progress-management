package com.tland.landsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Cho phép tất cả các request OPTIONS
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // Cho phép truy cập các endpoint không cần xác thực
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/users/**").permitAll()
                        .requestMatchers("/users/**").permitAll()
                        .requestMatchers("/api/areas/**").permitAll()

                        // Cho phép các request liên quan đến lands
                        .requestMatchers(HttpMethod.POST, "/api/lands").permitAll()
                        .requestMatchers("/api/lands", "/api/lands/**").permitAll()

                        // Cho phép truy cập owners
                        .requestMatchers("/api/owners/**").permitAll()
                        .requestMatchers("/owners/**").permitAll()

                        // Cho phép truy cập các endpoint của Swagger
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll()

                        // Cho phép tất cả các phương thức cho jobs và jobtypes
                        .requestMatchers("/api/jobs/**").permitAll()
                        .requestMatchers("/api/jobtypes/**").permitAll()

                        // 🔥 Thêm WorkGroups vào đây để tránh lỗi 403
                        .requestMatchers("/api/workgroups/**").permitAll()
                        .requestMatchers("/workgroups/**").permitAll()
                        // Cho phép truy cập report
                        .requestMatchers("/api/reports/**").permitAll()
                        .requestMatchers("/reports/**").permitAll()


                        // Các request khác cần xác thực
                        .anyRequest().authenticated()
                );
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
