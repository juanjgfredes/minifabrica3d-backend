package com.backend.minifabrica3d.infra.security.adapters;

import com.backend.minifabrica3d.infra.driven.security.adapters.SrpingAuthManagerAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith( MockitoExtension.class )
public class SrpingAuthManagerAdapterTest {

    @InjectMocks
    private SrpingAuthManagerAdapter srpingAuthManagerAdapter;

    @Mock
    private AuthenticationManager authManager;

    @Test
    public void isAuthenticateTest() {
        String emailOrUsername = "testuser";
        String password = "password";

        when(authManager.authenticate(new UsernamePasswordAuthenticationToken(emailOrUsername, password)))
                .thenReturn(new UsernamePasswordAuthenticationToken(emailOrUsername, password));

        boolean result = srpingAuthManagerAdapter.isAuthenticate(emailOrUsername, password);

        verify(authManager).authenticate(new UsernamePasswordAuthenticationToken(emailOrUsername, password));

        assertEquals(true, result);
    }

    @Test
    public void isAuthenticateTestFailure() {
        String emailOrUsername = "testuser";
        String password = "incorrectPassword";

        when(authManager.authenticate( new UsernamePasswordAuthenticationToken( emailOrUsername, password )))
                .thenThrow( new AuthenticationCredentialsNotFoundException( "error" ));

        boolean result = srpingAuthManagerAdapter.isAuthenticate( emailOrUsername, password );

        verify( authManager ).authenticate( new UsernamePasswordAuthenticationToken(emailOrUsername, password ));

        assertEquals(false, result);
    }

}
