package com.backend.minifabrica3d.infra.driver.dto;

import java.util.List;

public record UserDto(

        Long id,
        String username,
        String email,
        String password,
        boolean isActive,
        List<AddressDto> addresses,
        List<String> rol

) {
}
