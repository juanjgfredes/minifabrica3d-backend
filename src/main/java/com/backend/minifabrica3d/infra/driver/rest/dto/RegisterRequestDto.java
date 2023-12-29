package com.backend.minifabrica3d.infra.driver.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDto(
        @Email( message = "formato de e-mail no valido" )
        String email,

        @NotBlank( message = "la contraseña no puede estar vacia" )
        @Size( min = 6, message = "la contraseña debe tener minimo 6 caracteres" )
        String password,

        String username

) {
}
