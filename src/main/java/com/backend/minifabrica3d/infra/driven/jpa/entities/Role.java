package com.backend.minifabrica3d.infra.driven.jpa.entities;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {

    ADMIN,
    USER;

    public SimpleGrantedAuthority getAuthority() {
        return new SimpleGrantedAuthority( "ROLE_".concat( this.name() ) );
    }
}
