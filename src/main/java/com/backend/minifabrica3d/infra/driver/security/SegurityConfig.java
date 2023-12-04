package com.backend.minifabrica3d.infra.driver.security;

import com.backend.minifabrica3d.infra.driven.security.config.UserAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SegurityConfig {

    private final UserAuthProvider userAuthProvider;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http ) throws Exception {
        return http.csrf( AbstractHttpConfigurer::disable )
                .cors( cors -> cors.configure( http ) )
                .sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
                .authorizeHttpRequests( request -> {
                    request.requestMatchers(
                                    HttpMethod.POST,
                                    "/auth/register",
                                    "/auth/login" ).permitAll()
                            .anyRequest().authenticated();
                } )
                .authenticationProvider( authenticationProvider )
                .addFilterBefore( new JwtAuthFilter( userAuthProvider ), UsernamePasswordAuthenticationFilter.class )
                .build();
    }

}
