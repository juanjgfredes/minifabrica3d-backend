package com.backend.minifabrica3d.infra.rest.controllers;

import com.backend.minifabrica3d.application.ports.driver.rest.authorization.AuthRestPort;
import com.backend.minifabrica3d.domain.model.User;
import com.backend.minifabrica3d.infra.driven.jpa.entities.UserEntity;
import com.backend.minifabrica3d.infra.driven.security.config.UserAuthProvider;
import com.backend.minifabrica3d.infra.driver.rest.dto.LoginRequestDto;
import com.backend.minifabrica3d.infra.driver.rest.dto.RegisterRequestDto;
import com.backend.minifabrica3d.infra.driver.rest.dto.UserDto;
import com.backend.minifabrica3d.infra.driver.rest.mappers.UserRestMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserRestMapper userMapper;
    @MockBean
    private AuthRestPort authRest;
    @MockBean
    private UserAuthProvider authProvider;

    private UserEntity userEntity;
    private User userModel;
    private UserDto userDto;


    @BeforeEach
    void setUp() {
        userEntity = UserEntity.builder()
                .id(1)
                .email( "test@gmail.com" )
                .username( "TestName" )
                .password( "testPassword" )
                .isActive( true )
                .build();

        userModel = User.builder()
                .id(1L)
                .email( "test@gmail.com" )
                .username( "TestName" )
                .password( "testPassword" )
                .isActive( true )
                .build();

        userDto = new UserDto(
                1L,
                "TestName",
                "test@gmail.com",
                "testPassword",
                true,
                null,
                null
        );
    }

    @Test
    void registerTestSuccess() throws Exception {
        RegisterRequestDto registerRequestDto = new RegisterRequestDto(
                "test@gmail.com", "testPassword", "TestName"
        );
        when( userMapper.toUserModel( registerRequestDto )).thenReturn( userModel );
        when( authRest.register( userModel )).thenReturn( userModel );
        when( authProvider.createToken( userModel )).thenReturn( "token" );
        when( userMapper.toUserDto( userModel )).thenReturn( userDto );

        ResultActions results = mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content( objectMapper.writeValueAsString( registerRequestDto )));

        results.andExpect( status().is( 200 ))
                .andExpect( jsonPath( "$.user.id" ).value( 1 ))
                .andExpect( jsonPath( "$.user.username" ).value( "TestName" ))
                .andExpect( jsonPath( "$.user.email" ).value( "test@gmail.com" ))
                .andExpect( jsonPath( "$.user.password" ).value( "testPassword" ))
                .andExpect( jsonPath( "$.user.isActive" ).value( true ))
                .andExpect( jsonPath( "$.token" ).value( "token" ));
    }

    @Test
    void loginTestSuccess() throws Exception {
        LoginRequestDto loginRequestDto = new LoginRequestDto(
                "test@gmail.com", "testPassword"
        );
        when( userMapper.toUserModel( loginRequestDto )).thenReturn( userModel );
        when( authRest.login( userModel )).thenReturn( userModel );
        when( authProvider.createToken( userModel )).thenReturn( "token" );
        when( userMapper.toUserDto( userModel )).thenReturn( userDto );

        ResultActions results = mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content( objectMapper.writeValueAsString( loginRequestDto )));

        results.andExpect( status().is(200) )
                .andExpect( jsonPath( "$.user.id" ).value( 1 ))
                .andExpect( jsonPath( "$.user.username" ).value( "TestName" ))
                .andExpect( jsonPath( "$.user.email" ).value( "test@gmail.com" ))
                .andExpect( jsonPath( "$.user.password" ).value( "testPassword" ))
                .andExpect( jsonPath( "$.user.isActive" ).value( true ))
                .andExpect( jsonPath( "$.token" ).value( "token" ));
    }
}