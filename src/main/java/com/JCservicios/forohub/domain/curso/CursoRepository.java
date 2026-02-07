package com.JCservicios.forohub.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CursoRepository extends JpaRepository<Curso,Long> {
    boolean existsByNombre(String nombre);

    Page<Curso> findAll(Pageable pageable);


    boolean existsByNombreAndIdNot( String nombre, Long id);
}
