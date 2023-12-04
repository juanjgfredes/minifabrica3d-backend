package com.backend.minifabrica3d.infra.driver.rest.dto;

public record LoginResponseDto(

        UserDto user,
        String token

) {
}
