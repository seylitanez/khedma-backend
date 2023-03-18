package com.example.khedmabackend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.khedmabackend.Utils.Constantes.EMPLOYEUR;
import static com.example.khedmabackend.Utils.Constantes.MODERATEUR;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFiIter jwtAuthFilter;
    @Bean
    //filter des autorisation d'acces
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        //si ya un probleme dans cross origine c ici qu'il faut modifier

        http
                .csrf().disable()
                .cors().disable()
                .authorizeHttpRequests().requestMatchers("/api/v1/employeur/**").hasAnyAuthority(EMPLOYEUR)
                .and()
                .authorizeHttpRequests().requestMatchers("/api/v1/auth/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
