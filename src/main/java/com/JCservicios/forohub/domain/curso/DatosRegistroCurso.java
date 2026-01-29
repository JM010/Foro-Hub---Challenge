package com.JCservicios.forohub.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record DatosCurso(
        @NotBlank @Size(max = 100) String nombre,
        @NotNull String categoria
) {
}
