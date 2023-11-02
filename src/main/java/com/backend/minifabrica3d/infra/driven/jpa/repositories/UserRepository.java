package com.backend.minifabrica3d.infra.driven.jpa.repositories;

import com.backend.minifabrica3d.infra.driven.jpa.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail( String email );
    boolean existsByEmail( String email );

}
