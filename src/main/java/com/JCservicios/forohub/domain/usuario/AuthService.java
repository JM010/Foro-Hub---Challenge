package com.JCservicios.forohub.domain.usuario;

import com.JCservicios.forohub.domain.perfil.PerfilRepository;
import com.JCservicios.forohub.domain.perfil.Rol;
import com.JCservicios.forohub.domain.usuario.exepciones.UsuarioYaRegistradoException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final PerfilRepository perfilRepository;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, PerfilRepository perfilRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.perfilRepository = perfilRepository;
    }


    public RegistroResponse registrarUsuario(DTORegistro registroUsuarioDTO) {

        if (usuarioRepository.existsByEmail(registroUsuarioDTO.email())) {
            throw new UsuarioYaRegistradoException(" El usuario con email " + registroUsuarioDTO.email() + " ya está registrado.");
        }
        String encodedPassword = passwordEncoder.encode(registroUsuarioDTO.password());

        Usuario nuevoUsuario = new Usuario(
                registroUsuarioDTO.nombre(),
                registroUsuarioDTO.email(),
                perfilRepository.findByRol(Rol.ROLE_USER).orElseThrow(() -> new IllegalStateException("Rol de usuario no encontrado")),
                encodedPassword
        );
        usuarioRepository.save(nuevoUsuario);

        return  new RegistroResponse(nuevoUsuario, "Registro exitoso");
    }

}
