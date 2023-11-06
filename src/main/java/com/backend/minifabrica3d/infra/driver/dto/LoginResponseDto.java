package com.backend.minifabrica3d.infra.driver.dto;

public record LoginResponseDto(

        UserDto user,
        String token

) {
}
