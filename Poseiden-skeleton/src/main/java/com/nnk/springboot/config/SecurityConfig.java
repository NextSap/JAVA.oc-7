package com.nnk.springboot.config;

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
        http.formLogin(formLogin -> formLogin.defaultSuccessUrl("/bidList/list").permitAll())
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.logoutUrl("/app/logout").logoutSuccessUrl("/").permitAll())
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.accessDeniedPage("/app/error"))
                .authorizeHttpRequests(api ->
                        api
                                .requestMatchers("/static/**").permitAll()
                                .requestMatchers("/", "/login").permitAll()
                                .requestMatchers("/bidList/**", "/rating/**", "/ruleName/**", "/trade/**", "/curvePoint/**", "/app/error", "/app/logout").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/user/**").hasAuthority("ADMIN")
                                .anyRequest().authenticated());
        return http.build();
    }
}

