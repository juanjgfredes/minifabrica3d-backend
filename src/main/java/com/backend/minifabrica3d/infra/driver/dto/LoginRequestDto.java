package com.backend.minifabrica3d.infra.driver.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDto(

        @Email( message = "formato de e-mail no valido" )
        String email,

        @NotBlank( message = "la contraseña no puede estar vacia" )
        @Size( min = 8, message = "la contraseña debe tener minimo 6 caracteres" )
        String password

) {
}