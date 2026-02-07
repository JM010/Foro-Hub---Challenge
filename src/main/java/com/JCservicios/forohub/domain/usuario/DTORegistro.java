package com.JCservicios.forohub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record DTORegistro(
       @NotBlank String nombre,
       @NotBlank @Email String email,
       @NotBlank @Size(min = 8, max = 20) String password
) {
}
