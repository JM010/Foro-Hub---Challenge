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

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;
    private LocalDateTime fechaCreacion;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    private String solucion;

    public void setRopico(Topico topico) {
        this.topico = topico;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }
}
