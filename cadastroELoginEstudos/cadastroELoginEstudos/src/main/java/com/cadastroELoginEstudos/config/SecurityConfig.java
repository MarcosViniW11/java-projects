package com.cadastroELoginEstudos.config;

import com.cadastroELoginEstudos.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final CustomAuthEntryPoint authEntryPoint;
    private final CustomAcessDeniedHandler acessDeniedHandler;

    public SecurityConfig(JwtFilter filter, CustomAuthEntryPoint authEntryPoint, CustomAcessDeniedHandler acessDeniedHandler){
        this.jwtFilter=filter;
        this.authEntryPoint=authEntryPoint;
        this.acessDeniedHandler=acessDeniedHandler;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .cors(cors->{})
                .csrf(csrf-> csrf.disable())
                .sessionManagement(sm->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling(ex->ex
                        .authenticationEntryPoint(authEntryPoint)
                        .accessDeniedHandler(acessDeniedHandler)
                ).authorizeHttpRequests(auth->auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }

}
