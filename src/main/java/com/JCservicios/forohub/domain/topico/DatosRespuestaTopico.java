package com.JCservicios.forohub.domain.topico;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        String fechaCreacion,
        String autorNombre,
        String cursoNombre
) {
    DatosRespuestaTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion().toString(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}
