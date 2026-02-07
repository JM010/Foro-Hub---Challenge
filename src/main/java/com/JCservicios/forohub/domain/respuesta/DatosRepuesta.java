package com.JCservicios.forohub.domain.respuesta;

import com.JCservicios.forohub.domain.topico.Topico;

import java.time.LocalDateTime;

public record DatosRepuesta(
        Long id,
        String mensaje,
        String autorNombre,
        LocalDateTime fechaCreacion,
        boolean solucion) {
    public DatosRepuesta(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getAutor().getNombre(),
                respuesta.getFechaCreacion(),
                respuesta.isSolucion()
        );
    }
}
