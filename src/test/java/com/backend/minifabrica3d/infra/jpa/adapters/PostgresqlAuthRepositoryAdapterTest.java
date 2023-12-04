package com.backend.minifabrica3d.infra.jpa.adapters;

import com.backend.minifabrica3d.domain.model.User;
import com.backend.minifabrica3d.infra.driven.jpa.adapters.repositories.PostgresqlAuthRepositoryAdapter;
import com.backend.minifabrica3d.infra.driven.jpa.entities.UserEntity;
import com.backend.minifabrica3d.infra.driven.jpa.mappers.UserJpaMapper;
import com.backend.minifabrica3d.infra.driven.jpa.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith( MockitoExtension.class )
public class PostgresqlAuthRepositoryAdapterTest {

    @InjectMocks
    private PostgresqlAuthRepositoryAdapter postgresqlAuthRepositoryAdapter;

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserJpaMapper userJpaMapper;

    @Test
    public void getByEmailTest(){
        String email = "prueba@test.com";
        UserEntity userEntity = UserEntity.builder()
                .id( 2 )
                .email( email )
                .username( "prueba" )
                .password( "passwodrPrueba" )
                .build();
        User userModel = User.builder()
                .id( 2L )
                .email( email )
                .username( "prueba" )
                .password( "passwodrPrueba" )
                .build();

        when( userRepository.findByEmail( email )).thenReturn( Optional.of( userEntity ));
        when( userJpaMapper.toUserModel( userEntity )).thenReturn( userModel );

        Optional<User> userFound = postgresqlAuthRepositoryAdapter.getByEmail( email );

        verify( userRepository ).findByEmail( email );
        verify(userJpaMapper).toUserModel( userEntity );

        assertAll(() -> {
            assertEquals( userEntity.getId(), userFound.get().getId() );
            assertEquals( userEntity.getEmail(), userFound.get().getEmail() );
            assertEquals( userEntity.getPassword(), userFound.get().getPassword() );
            assertEquals( userEntity.getUsername(), userFound.get().getUsername() );
        });
    }

    @Test
    public void saveRegisterTest(){
        UserEntity userEntity = UserEntity.builder()
                .id( 2 )
                .email( "prueba@test.com" )
                .username( "prueba" )
                .password( "passwodrPrueba" )
                .build();
        User userModel = User.builder()
                .id( 2L )
                .email( "prueba@test.com" )
                .username( "prueba" )
                .password( "passwodrPrueba" )
                .build();

        when( userJpaMapper.toUserEntity( userModel )).thenReturn( userEntity );
        when( userRepository.save( userEntity )).thenReturn( userEntity );
        when( userJpaMapper.toUserModel( userEntity )).thenReturn( userModel );

        User userResgitered = postgresqlAuthRepositoryAdapter.saveRegister( userModel );

        verify(userJpaMapper).toUserEntity( userModel );
        verify( userRepository ).save( userEntity );
        verify(userJpaMapper).toUserModel( userEntity );

        assertAll(() -> {
                assertEquals( userModel.getId(), userResgitered.getId() );
                assertEquals( userModel.getEmail(), userResgitered.getEmail() );
                assertEquals( userModel.getPassword(), userResgitered.getPassword() );
                assertEquals( userModel.getUsername(), userResgitered.getUsername() );
        });
    }

    @Test
    public void existByEmailTest(){
        String email = "prueba@test.com";
        when( userRepository.existsByEmail( email )).thenReturn( true );

        boolean existByEmail = postgresqlAuthRepositoryAdapter.ExistByEmail( email );

        verify( userRepository ).existsByEmail( email );
        assertEquals( true, existByEmail );
    }

}
