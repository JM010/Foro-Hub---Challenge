package com.JCservicios.forohub.domain.respuesta;


import com.JCservicios.forohub.domain.exception.ValidationException;
import com.JCservicios.forohub.domain.perfil.Rol;
import com.JCservicios.forohub.domain.topico.StatusTopico;
import com.JCservicios.forohub.domain.topico.Topico;
import com.JCservicios.forohub.domain.topico.TopicoRepository;
import com.JCservicios.forohub.domain.usuario.Usuario;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RespuestaService {
    private final RespuestaRepository respuestaRepository;

    private final TopicoRepository topicoRepository;

    public RespuestaService(RespuestaRepository repository, TopicoRepository topicoRepository) {
        this.respuestaRepository = repository;
        this.topicoRepository = topicoRepository;
    }




    public DatosRepuesta agregarRespuesta(Long id, @Valid DatosRegistroRespuesta datos, Usuario usuario) {
        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));

        Respuesta respuesta =  new Respuesta(datos.mensaje(),usuario,topico);
        topico.setStatus(StatusTopico.RESPONDIDO);
        respuestaRepository.save(respuesta);
        return new DatosRepuesta(respuesta);
    }

    @Transactional
    public DatosRepuesta actualizarRespuesta(Long idTopico, Long idRespuesta, Usuario usuario, @Valid DatosActualizarRespuesta datos) {
         Respuesta respuesta = respuestaRepository.findById(idRespuesta).orElseThrow(() -> new EntityNotFoundException("Respuesta no encontrada"));

         if (!respuestaRepository.existsByIdAndTopicoId(idRespuesta,idTopico)) {
             throw new ValidationException("La respuesta no pertenece al tópico");
         }

         boolean esAutor = respuesta.getAutor().getId().equals(usuario.getId());
         boolean esAdmin = usuario.getAuthorities().stream()
                 .anyMatch(a -> a.getAuthority().equals(Rol.ROLE_ADMIN));

        if (!esAutor && !esAdmin) {
            throw  new AccessDeniedException("No tiene permiso para editar esta respuesta");
        }
         respuesta.actualizarMensaje(datos.mensaje());

        return new DatosRepuesta(respuesta);

    }

    public Page<DatosRepuesta> listar(Pageable pageable, Long idTopico) {
        if (!topicoRepository.existsById(idTopico)) {
            throw new EntityNotFoundException("El ID del tópico no existe");
        }
        return respuestaRepository.findByTopicoId(idTopico,pageable).map(DatosRepuesta::new);
    }

    public void eliminarRespuesta(Long idRespuesta) {
        respuestaRepository.deleteById(idRespuesta);
    }

}
