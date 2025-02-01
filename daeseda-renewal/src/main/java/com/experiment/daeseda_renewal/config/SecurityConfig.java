package com.experiment.daeseda_renewal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/**").permitAll() // 로그인 없이 접근 가능한 경로
                .anyRequest().authenticated() // 나머지 요청은 인증 필요
                .and()
                .formLogin().disable() // 기본 로그인 페이지 비활성화
                .csrf().disable(); // CSRF 보호 비활성화 (필요시)

        return http.build();  // HttpSecurity 설정을 반환
    }
}
