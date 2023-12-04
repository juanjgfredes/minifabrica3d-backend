package com.backend.minifabrica3d.infra.driver.security;

import com.backend.minifabrica3d.infra.driven.security.config.UserAuthProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserAuthProvider userAuthProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader( HttpHeaders.AUTHORIZATION );

        if( header != null && header.startsWith( "Bearer ") ){
            String token = header.replace( "Bearer ", "" );
            SecurityContextHolder.getContext().setAuthentication(
                    userAuthProvider.validityToken( token )
            );
        }

        filterChain.doFilter( request, response );
    }
}
