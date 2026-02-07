package com.JCservicios.forohub.domain.topico;

import org.springframework.stereotype.Component;

@Component
public class TopicoSecurity {

    private final TopicoRepository topicoRepository;

    public TopicoSecurity(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    public boolean isAutor(Long topicoId, Long usuarioId) {
        return topicoRepository.existsByIdAndAutorId(topicoId, usuarioId);
    }

}
