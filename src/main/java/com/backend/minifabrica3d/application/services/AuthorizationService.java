package com.backend.minifabrica3d.application.services;

import com.backend.minifabrica3d.application.ports.driven.security.authentication.AuthRepositoryPort;
import com.backend.minifabrica3d.application.ports.driven.security.authentication.AuthenticationPort;
import com.backend.minifabrica3d.application.ports.driven.security.authentication.PasswordEncodePort;
import com.backend.minifabrica3d.application.ports.driver.rest.authorization.AuthRestPort;
import com.backend.minifabrica3d.application.ports.driver.rest.exceptions.CustomException;
import com.backend.minifabrica3d.application.ports.driver.rest.exceptions.ExceptionMessages;
import com.backend.minifabrica3d.domain.model.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthorizationService implements AuthRestPort {

    private final AuthRepositoryPort authRepository;
    private final PasswordEncodePort passwordEncode;
    private final AuthenticationPort auth;

    @Override
    public User login( User user ){
        String email = user.getEmail();
        User userFound = authRepository.getByEmail( email )
                .orElseThrow(() -> new CustomException( ExceptionMessages.AUTH_EMAIL_NOT_FOUND, 404 ));

         if ( !auth.isAuthenticate( user.getEmail(), user.getPassword() )){
             throw new CustomException( ExceptionMessages.AUTH_INVALID_CREDENTIALS_UNAUTHORIZED, 401 );
         }

        return userFound;
    }

    @Override
    public User register( User user ) {
        String email = user.getEmail();
        if ( authRepository.ExistByEmail( email )){
            throw new CustomException( ExceptionMessages.AUTH_EMAIL_EXISTS_CONFLICT, 409 );
        }

        user.setPassword(
                passwordEncode.encode( user.getPassword() )
        );
        User userRegistered = authRepository.saveRegister( user );

        return userRegistered;
    }

}
