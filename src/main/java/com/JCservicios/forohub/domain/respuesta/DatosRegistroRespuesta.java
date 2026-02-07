package com.JCservicios.forohub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record DatosRegistroRespuesta(
        @NotBlank @Size( max = 1000) String mensaje
) {
}
