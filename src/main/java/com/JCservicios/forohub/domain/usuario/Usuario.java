package com.JCservicios.forohub.domain.usuario;

import com.JCservicios.forohub.domain.perfil.Perfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "usuarios")
@EqualsAndHashCode (of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    private String nombre;
    private String email;
    private String contrasena;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuarios_perfiles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id")
    )
    private Set<Perfil>perfiles;

}

