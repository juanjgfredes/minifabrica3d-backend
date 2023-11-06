package com.backend.minifabrica3d.infra.driven.security.config;

import com.backend.minifabrica3d.infra.driven.jpa.repositories.UserRepository;
import com.backend.minifabrica3d.infra.exception.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Configuration
public class ApplicationConfig {

    private final UserRepository userRepository;

    @Bean
    private UserDetailsService userDetailsService() {
        return email -> userRepository.findByEmail( email )
                .orElseThrow( () -> new AppException("no se encontro el usuario con el email " + email, HttpStatus.NOT_FOUND));
    }

    @Bean
    private AuthenticationManager authenticationManager( AuthenticationConfiguration config ) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    private AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder( passwordEncoder() );
        authenticationProvider.setUserDetailsService( userDetailsService() );
        return authenticationProvider;
    }

}
