package com.JCservicios.forohub.domain.topico;

import com.JCservicios.forohub.domain.curso.Curso;
import com.JCservicios.forohub.domain.respuesta.Respuesta;
import com.JCservicios.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "respuesta_id")
    private Respuesta respuesta;

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }


    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

}
