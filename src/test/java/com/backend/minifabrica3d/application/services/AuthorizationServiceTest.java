package com.backend.minifabrica3d.application.services;

import com.backend.minifabrica3d.application.ports.driven.repositories.AuthRepositoryPort;
import com.backend.minifabrica3d.application.ports.driven.security.AuthenticationPort;
import com.backend.minifabrica3d.application.ports.driven.security.PasswordEncodePort;
import com.backend.minifabrica3d.application.exceptions.CustomException;
import com.backend.minifabrica3d.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AuthorizationServiceTest {

    @InjectMocks
    private AuthorizationService authorizationService;
    @Mock
    private AuthRepositoryPort authRepository;
    @Mock
    private PasswordEncodePort passwordEncode;
    @Mock
    private AuthenticationPort auth;

    private User user;

    @BeforeEach
    void setup() {
        user = new User();
        user.setEmail( "prueba@email.com" );
        user.setPassword( "passwordtest" );
    }

    @Test
    void loginTest() {
        String email = user.getEmail();

        when( authRepository.getByEmail( email )).thenReturn( Optional.of( user ));
        when( auth.isAuthenticate( user.getEmail(), user.getPassword() ) ).thenReturn( true );

        User userFound = authorizationService.login( user );

        verify( auth ).isAuthenticate( user.getEmail(), user.getPassword() );
        verify( authRepository ).getByEmail( email );

        assertAll(() -> {
            assertEquals( user.getEmail(), userFound.getEmail(), "El email no coincide" );
            assertEquals( user.getPassword(), userFound.getPassword(), "La contraseña no coincide" );
        });
    }

    @Test
    void loginExceptionWhenEmailNotFound() {
        String email = user.getEmail();

        when( authRepository.getByEmail( email )).thenReturn( Optional.empty() );

        assertThrows( CustomException.class, () -> authorizationService.login( user ));

        verify( authRepository ).getByEmail( email );
        verifyNoInteractions( auth );
    }

    @Test
    void loginExceptionWhenIsNotAuthenticate() {
        String email = user.getEmail();

        when( authRepository.getByEmail( email )).thenReturn( Optional.of( user ));
        when( auth.isAuthenticate( email, user.getPassword() )).thenReturn( false );

        assertThrows( CustomException.class, () -> authorizationService.login( user ));

        verify( authRepository ).getByEmail( email );
        verify( auth ).isAuthenticate( email, user.getPassword() );
    }

    @Test
    void registerTest() {
        String email = user.getEmail();
        String password = user.getPassword();
        String passwordPost = "passwordPostEncode";

        when( authRepository.ExistByEmail( email )).thenReturn( false );
        when( passwordEncode.encode( password )).thenReturn( passwordPost );
        when( authRepository.saveRegister( user )).thenReturn( user );

        User userRegistered = authorizationService.register( user );

        verify( authRepository ).ExistByEmail( email );
        verify( passwordEncode ).encode( password );
        verify( authRepository ).saveRegister( user );

        assertAll(() -> {
            assertEquals( user.getEmail(), userRegistered.getEmail(), "El email no coincide");
            assertEquals( passwordPost, userRegistered.getPassword(), "La contraseña no coincide" );
        });
    }

    @Test
    void registerExceptionWhenExistEmail() {
        String email = user.getEmail();

        when( authRepository.ExistByEmail( email )).thenReturn( true );

        assertAll(() -> {
            assertThrows( CustomException.class, () -> authorizationService.register( user ));
        });

        verify( authRepository ).ExistByEmail( email );
        verifyNoInteractions( passwordEncode );
    }

}
