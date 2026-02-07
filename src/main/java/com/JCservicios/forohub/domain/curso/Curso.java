package com.JCservicios.forohub.domain.curso;

import com.JCservicios.forohub.domain.topico.Topico;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity (name = "Curso")
@Table(name = "cursos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String categoria;
    @OneToMany(mappedBy = "curso")
    private List<Topico>topicos;

    public Curso(DatosRegistroCurso datos) {
        this.nombre = datos.nombre();
        this.categoria = datos.categoria();
    }

    public void actualizarDatos(@Valid DatosRegistroCurso datos) {
        if ( !datos.nombre().isBlank()) {
            this.nombre = datos.nombre();
        }
        if (!datos.categoria().isBlank()) {
            this.categoria = datos.categoria();
        }
    }
}
