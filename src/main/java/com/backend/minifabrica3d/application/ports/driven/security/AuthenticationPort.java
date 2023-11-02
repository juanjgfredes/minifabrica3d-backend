package com.backend.minifabrica3d.application.ports.driven.security;

public interface AuthenticationPort {

    boolean isAuthenticate( String emailOrUsername, String password );

}
