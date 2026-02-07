package com.JCservicios.forohub.domain.curso;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public Page<DatosRespuestaCurso> listar(Pageable pageable) {
        return cursoRepository.findAll(pageable).map(DatosRespuestaCurso::new);
    }


    @Transactional
    public DatosRespuestaCurso obtenerCursoPorId(Long id) {
        var curso = cursoRepository.findById(id);
        if (!curso.isPresent()) {
            throw new EntityNotFoundException( "Curso con ID " + id + " no encontrado.");
        }
        return new DatosRespuestaCurso(curso.get());

    }

    @Transactional
    public DatosRespuestaCurso actualizarCurso(@Valid DatosRegistroCurso datos, Long id) {
        var curso = cursoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Curso con ID " + id + " no encontrado."));
        if (cursoRepository.existsByNombreAndIdNot(datos.nombre(),id)) {
            throw new ValidationException("El curso con el nombre " + datos.nombre() + " ya existe.");
        }
        curso.actualizarDatos(datos);
        return new DatosRespuestaCurso(curso);
    }

    public void eliminarCurso(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new EntityNotFoundException("Curso con ID " + id + " no encontrado.");
        }
        cursoRepository.deleteById(id);
    }
}
