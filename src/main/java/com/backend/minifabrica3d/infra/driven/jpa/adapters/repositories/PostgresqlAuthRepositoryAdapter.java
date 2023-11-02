package com.backend.minifabrica3d.infra.driven.jpa.adapters.repositories;

import com.backend.minifabrica3d.application.ports.driven.repositories.AuthRepositoryPort;
import com.backend.minifabrica3d.domain.model.User;
import com.backend.minifabrica3d.infra.driven.jpa.entities.UserEntity;
import com.backend.minifabrica3d.infra.driven.jpa.mappers.UserMapper;
import com.backend.minifabrica3d.infra.driven.jpa.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PostgresqlAuthRepositoryAdapter implements AuthRepositoryPort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<User> getByEmail(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail( email );
        return userEntity.map( userMapper::toUserModel );
    }

    @Override
    public User saveRegister(User user) {
        UserEntity userEntity = userMapper.toUserEntity( user );
        UserEntity userSave = userRepository.save( userEntity );
        return userMapper.toUserModel( userSave );
    }

    @Override
    public boolean ExistByEmail(String email) {
        return userRepository.existsByEmail( email );
    }
}
