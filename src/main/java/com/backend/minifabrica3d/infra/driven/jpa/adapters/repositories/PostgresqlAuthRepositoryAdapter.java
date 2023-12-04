package com.backend.minifabrica3d.infra.driven.jpa.adapters.repositories;

import com.backend.minifabrica3d.application.ports.driven.repositories.AuthRepositoryPort;
import com.backend.minifabrica3d.domain.model.User;
import com.backend.minifabrica3d.infra.driven.jpa.entities.Rol;
import com.backend.minifabrica3d.infra.driven.jpa.entities.Role;
import com.backend.minifabrica3d.infra.driven.jpa.entities.UserEntity;
import com.backend.minifabrica3d.infra.driven.jpa.mappers.UserJpaMapper;
import com.backend.minifabrica3d.infra.driven.jpa.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class PostgresqlAuthRepositoryAdapter implements AuthRepositoryPort {

    private final UserRepository userRepository;
    private final UserJpaMapper userJpaMapper;

    @Override
    public Optional<User> getByEmail(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail( email );
        return userEntity.map( userJpaMapper::toUserModel );
    }

    @Override
    public User saveRegister(User user) {
        UserEntity userEntity = userJpaMapper.toUserEntity( user );
        userEntity.setRol( new Rol(( short ) 2, Role.USER ));
        UserEntity userSave = userRepository.save( userEntity );
        return userJpaMapper.toUserModel( userSave );
    }

    @Override
    public boolean ExistByEmail( String email ) {
        return userRepository.existsByEmail( email );
    }

    @Override
    public User getUserById(int id) {
        UserEntity userFound = userRepository.findById( (long) id ).get();
        User user = userJpaMapper.toUserModel( userFound );
        return user;
    }
}
