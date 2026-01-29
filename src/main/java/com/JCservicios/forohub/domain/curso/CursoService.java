package com.JCservicios.forohub.domain.curso;

import org.springframework.stereotype.Service;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public DatosRespuestaCurso crearCurso(DatosRegistroCurso datos) {
        if (cursoRepository.existsByNombre(datos.nombre())) {
            throw new IllegalStateException("El curso con el nombre " + datos.nombre() + " ya existe.");
        }
        Curso curso = new Curso(datos);
        cursoRepository.save(curso);
        return new DatosRespuestaCurso(curso.getId(), curso.getNombre(), curso.getCategoria());
    }


}
