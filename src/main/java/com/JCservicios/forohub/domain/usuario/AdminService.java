package com.JCservicios.forohub.domain.usuario;

import com.JCservicios.forohub.domain.perfil.Perfil;
import com.JCservicios.forohub.domain.perfil.PerfilRepository;
import com.JCservicios.forohub.domain.perfil.Rol;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {
    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;

    public AdminService(UsuarioRepository usuarioRepository,
                        PerfilRepository perfilRepository) {
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
    }

    @Transactional
    public void asignarRolAdmin(Long usuarioId){
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow( ()-> new EntityNotFoundException("El usuario con ese id no existe"));

        Perfil perfil = perfilRepository.findByRol(Rol.ROLE_ADMIN);

        usuario.getPerfiles().add(perfil);
    }
}
