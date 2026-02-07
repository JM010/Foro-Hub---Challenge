package com.JCservicios.forohub.domain.respuesta;

public record DatosSolucion(
        Long idRespuesta,
        Long idTopico,
        String mensaje,
        Boolean solucion
) {
    public DatosSolucion(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getTopico().getId(),
                respuesta.getMensaje(),
                respuesta.isSolucion()
        );
    }
}
