package com.backend.minifabrica3d.infra.driven.security.adapters;

import com.backend.minifabrica3d.application.ports.driven.security.PasswordEncodePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SpringPasswordEncodeAdapter implements PasswordEncodePort {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String encode(String password) {
        return passwordEncoder.encode( password );
    }

}
