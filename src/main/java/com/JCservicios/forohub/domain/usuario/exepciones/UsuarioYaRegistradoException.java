package com.JCservicios.forohub.domain.usuario.exepciones;

public class UsuarioYaRegistradoException extends RuntimeException {
    public UsuarioYaRegistradoException(String message) {
        super(message);
    }
}
