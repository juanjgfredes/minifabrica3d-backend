package com.backend.minifabrica3d.infra.driver.rest.controllers;

import com.backend.minifabrica3d.application.ports.driver.rest.authorization.AuthRestPort;
import com.backend.minifabrica3d.domain.model.User;
import com.backend.minifabrica3d.infra.driven.security.config.UserAuthProvider;
import com.backend.minifabrica3d.infra.driver.rest.dto.LoginRequestDto;
import com.backend.minifabrica3d.infra.driver.rest.dto.LoginResponseDto;
import com.backend.minifabrica3d.infra.driver.rest.dto.RegisterRequestDto;
import com.backend.minifabrica3d.infra.driver.rest.dto.UserDto;
import com.backend.minifabrica3d.infra.driver.rest.mappers.UserRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping( "/auth" )
public class AuthController {

    private final AuthRestPort authRest;
    private final UserRestMapper userMapper;
    private final UserAuthProvider authProvider;

    @PostMapping( "/register" )
    public ResponseEntity<LoginResponseDto> register( @RequestBody @Valid RegisterRequestDto registerDto ){
        User userToRegister = userMapper.toUserModel( registerDto );
        User userRegistered = authRest.register( userToRegister );
        LoginResponseDto loginResponseDto = getUserLogin( userRegistered );
        return ResponseEntity.ok( loginResponseDto );
    }

    @PostMapping( "/login" )
    public ResponseEntity<LoginResponseDto> login( @RequestBody @Valid LoginRequestDto loginRequestDto ){
        User userToLogin = userMapper.toUserModel( loginRequestDto );
        User userLogged = authRest.login( userToLogin );
        LoginResponseDto loginResponseDto = getUserLogin( userLogged );
        return ResponseEntity.ok( loginResponseDto );
    }

    private LoginResponseDto getUserLogin( User user ) {
        String token = authProvider.createToken( user );
        UserDto userDto = userMapper.toUserDto( user );
        return new LoginResponseDto( userDto, token );
    }

}
