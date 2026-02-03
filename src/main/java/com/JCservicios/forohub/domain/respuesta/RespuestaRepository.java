package com.JCservicios.forohub.domain.respuesta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {

    boolean existsByIdAndTopicoId(Long idRespuesta, Long idTopico);

    Page<Respuesta> findByTopicoId(Long idTopico, Pageable pageable);


}
