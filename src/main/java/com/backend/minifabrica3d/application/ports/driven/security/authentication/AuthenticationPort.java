package com.backend.minifabrica3d.application.ports.driven.security.authentication;

public interface AuthenticationPort {

    boolean isAuthenticate( String emailOrUsername, String password );

}
