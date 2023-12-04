package com.backend.minifabrica3d.infra.jpa.repositories;

import com.backend.minifabrica3d.infra.driven.jpa.entities.Rol;
import com.backend.minifabrica3d.infra.driven.jpa.entities.Role;
import com.backend.minifabrica3d.infra.driven.jpa.entities.UserEntity;
import com.backend.minifabrica3d.infra.driven.jpa.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest
//@AutoConfigureTestDatabase( connection = EmbeddedDatabaseConnection.H2 )
@ExtendWith( SpringExtension.class )
@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;

    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        Rol rol = new Rol(( short ) 1, Role.USER );
        entityManager.merge( rol );
        userEntity = UserEntity.builder()
                .id(1)
                .email( "test@gmail.com" )
                .username( "TestName" )
                .password( "testPassword" )
                .isActive( true )
                .rol( rol )
                .build();
        userRepository.save( userEntity );
    }

    @Test
    void findByEmailTestSuccess() {
        Optional<UserEntity> userEntityResult = userRepository.findByEmail( "test@gmail.com" );

        assertTrue( userEntityResult.isPresent() );
        assertAll(() -> {
            assertEquals( userEntity.getUsername(), userEntityResult.get().getUsername() );
            assertEquals( userEntity.getPassword(), userEntityResult.get().getPassword() );
            assertEquals( userEntity.getRol(), userEntityResult.get().getRol() );
        });
    }

    @Test
    void existsByEmailSuccess() {
        boolean existByEmailResult = userRepository.existsByEmail( "test@gmail.com" );

        assertTrue( existByEmailResult );
    }

    @Test
    void existsByEmailNotFound() {
        boolean existByEmailResult = userRepository.existsByEmail( "wrong@gmail.com" );

        assertFalse( existByEmailResult );
    }

}