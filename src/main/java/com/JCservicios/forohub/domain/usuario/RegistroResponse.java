package com.JCservicios.forohub.domain.usuario;

import com.JCservicios.forohub.domain.perfil.Perfil;

import java.util.Set;

public record RegistroResponse(
        Long id,
        String nombre,
        String email,
        Set<Perfil> perfiles,
        String mensaje
)
{
    RegistroResponse(Usuario usuario, String mensaje) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPerfiles(),
                mensaje
        );
    }
}
