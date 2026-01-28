package com.JCservicios.forohub.domain.usuario;

public record RegistroResponse(
        Long id,
        String nombre,
        String email,
        String mensaje
)
{
    RegistroResponse(Usuario usuario, String mensaje) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                mensaje
        );
    }
}
