package com.JCservicios.forohub.domain.topico;

import com.JCservicios.forohub.domain.curso.CursoRepository;
import com.JCservicios.forohub.domain.exception.ValidationException;
import com.JCservicios.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TopicoService {
    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final  CursoRepository cursoRepository;


    public TopicoService(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository, CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    public DatosRespuestaTopico crearTopico(DatosRegistroTopico datos) {
        if (topicoRepository.existsByTituloAndCursoId(datos.titulo(), datos.idCurso())) {
            throw new ValidationException("Ya existe un tópico con el mismo título en este curso");
        }
        if (!usuarioRepository.existsById(datos.idAutor())){
            throw new ValidationException("El ID del autor no existe");
        }
        var usuario = usuarioRepository.findById(datos.idAutor()).get();
        var curso = cursoRepository.findById(datos.idCurso()).orElseThrow(() -> new ValidationException("El ID del curso no existe"));

        var topico = new Topico(datos);
        topico.setAutor(usuario);
        topico.setCurso(curso);
        topicoRepository.save(topico);

        return new DatosRespuestaTopico(topico);
    }


    public Page<DatosDetalleTopico> listar(Pageable pageable) {
        return topicoRepository.findAll(pageable).map(DatosDetalleTopico::new);
    }

    @Transactional
    public DatosDetalleTopico obtenerTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new ValidationException("El ID del tópico no existe");
        }
        var topico = topicoRepository.getReferenceById(id);
        return new DatosDetalleTopico(topico);
    }

    @Transactional
    public DatosDetalleTopico actualizarTopico(@Valid DatosRegistroTopico datos, Long id) {
        var topico = topicoRepository.findById(id).orElseThrow(() -> new ValidationException("El ID del tópico no existe"));

        if (topicoRepository.existsByTituloAndCursoId(datos.titulo(), datos.idCurso())) {
            throw new ValidationException("Ya existe un tópico con el mismo título en este curso");
        }
        
        topico.actualizarInformacion(datos);
        return new DatosDetalleTopico(topico);
    }

    @Transactional
    public void eliminarTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new ValidationException("El ID del tópico no existe");
        }
        topicoRepository.deleteById(id);
    }
}
