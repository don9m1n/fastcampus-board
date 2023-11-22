package com.fastcampus.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll()
                )
                .formLogin(formLogin -> formLogin
                        .isCustomLoginPage() // 커스텀 로그인 페이지가 없으면 기본 로그인 화면으로 ㄱㄱ
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                )
                .build();
    }

}
