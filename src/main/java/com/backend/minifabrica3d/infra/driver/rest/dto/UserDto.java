package com.backend.minifabrica3d.infra.driver.rest.dto;

import java.util.List;

public record UserDto(

        Long id,
        String username,
        String email,
        boolean isActive,
        List<AddressDto> addresses,
        String rol

) {
}
