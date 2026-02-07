package com.JCservicios.forohub.domain.respuesta;

import org.springframework.stereotype.Component;

@Component
public class RespuestaSecurity {

    private final RespuestaRepository respuestaRepository;

    public RespuestaSecurity(RespuestaRepository respuestaRepository) {
        this.respuestaRepository = respuestaRepository;
    }

    public boolean isAutor(Long respuestaId, Long usuarioId) {

        if (!respuestaRepository.existsByIdAndAutorId(respuestaId, usuarioId)) {
            throw new SecurityException("No tienes permiso para realizar esta acción");
        }

        return true;
    }
}
