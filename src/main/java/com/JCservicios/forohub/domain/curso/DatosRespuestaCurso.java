package com.JCservicios.forohub.domain.curso;

import lombok.extern.java.Log;

public record DatosRespuestaCurso(
        Long id,
        String nombre,
        String categoria

) {
    DatosRespuestaCurso(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}
