package com.JCservicios.forohub.domain.respuesta;

import com.JCservicios.forohub.domain.topico.StatusTopico;
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

    private boolean solucion;

    public void setRopico(Topico topico) {
        this.topico = topico;
    }

    public Respuesta(String mensaje,Usuario autor, Topico topico) {
        this.mensaje = mensaje;
        this.autor = autor;
        this.topico = topico;
    }

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
        this.solucion= false;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public void actualizarMensaje(String mensaje) {
        if (!mensaje.isBlank()) {
            this.mensaje = mensaje;
        }
    }
}
