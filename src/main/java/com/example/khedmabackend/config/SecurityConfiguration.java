package com.example.khedmabackend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.khedmabackend.Utils.Constantes.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFiIter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    @Bean
    //filter des autorisation d'acces
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .cors().disable()
                .authorizeHttpRequests().requestMatchers("/api/v1/employe/**").hasAuthority(EMPLOYE)
                .and()
                .authorizeHttpRequests().requestMatchers("/api/v1/employeur/**").hasAuthority(EMPLOYEUR)
                .and()
                .authorizeHttpRequests().requestMatchers("/api/v1/auth/**").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/api/v1/search/**").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/images/**").permitAll()
//                .and()
//                .authorizeHttpRequests().requestMatchers("/api/v1/me").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
