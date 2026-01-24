package com.JCservicios.forohub.domain.respuesta;

import com.JCservicios.forohub.domain.topico.Topico;
import com.JCservicios.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "respuestas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    private Topico ropico;
    private LocalDateTime fechaCreacion;
    private Usuario autor;
    private String solucion;
}
