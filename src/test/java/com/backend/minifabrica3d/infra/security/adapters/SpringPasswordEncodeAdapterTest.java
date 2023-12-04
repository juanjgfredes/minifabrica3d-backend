package com.backend.minifabrica3d.infra.security.adapters;

import com.backend.minifabrica3d.infra.driven.security.adapters.SpringPasswordEncodeAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith( MockitoExtension.class )
public class SpringPasswordEncodeAdapterTest {

    @InjectMocks
    private SpringPasswordEncodeAdapter springPasswordEncodeAdapter;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void encodeTest() {
        String passwordTest = "password";
        String encodedPassword = "encodedPassword";

        when(passwordEncoder.encode( passwordTest )).thenReturn( encodedPassword );

        String result = springPasswordEncodeAdapter.encode( passwordTest );

        verify( passwordEncoder ).encode( passwordTest );

        assertAll(() -> {
            assertEquals(encodedPassword, result);
        });
    }

}
