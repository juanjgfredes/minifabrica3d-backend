package com.backend.minifabrica3d.application.ports.driven.repositories;

import com.backend.minifabrica3d.domain.model.User;

import java.util.Optional;

public interface AuthRepositoryPort {

    Optional<User> getByEmail( String email );
    User saveRegister( User user );
    boolean ExistByEmail( String email );
    User getUserById( int id );

}
