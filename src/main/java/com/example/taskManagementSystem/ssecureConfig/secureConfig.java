package com.example.taskManagementSystem.ssecureConfig;

import com.example.taskManagementSystem.JwtToken.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class secureConfig {

    private JwtAuthFilter jwtAuthFilter;

    secureConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public PasswordEncoder pEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
        
        .csrf(csrf -> csrf.disable())
        .formLogin(f->f.disable())
        .httpBasic(h->  h.disable())
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) ;
        return http.build();
    }
}
