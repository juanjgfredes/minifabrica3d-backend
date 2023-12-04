package com.backend.minifabrica3d.infra.driven.security.adapters;

import com.backend.minifabrica3d.application.ports.driven.security.AuthenticationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SrpingAuthManagerAdapter implements AuthenticationPort {

    private final AuthenticationManager authManager;

    @Override
    public boolean isAuthenticate(String emailOrUsername, String password) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            emailOrUsername,
                            password
                    )
            );
        } catch ( AuthenticationException authEx ) {
            return false;
        }
        return true;
    }

}
