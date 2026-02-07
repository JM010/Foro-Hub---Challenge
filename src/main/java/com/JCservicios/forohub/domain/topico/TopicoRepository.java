package com.JCservicios.forohub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico,Long> {

    boolean existsByTituloAndCursoId(String titulo, Long cursoId);

    boolean existsByIdAndAutorId(Long id, Long usuarioId);

    Page<Topico>findAll(Pageable pageable);

}
