package com.JCservicios.forohub.domain.usuario;

import com.JCservicios.forohub.domain.perfil.Perfil;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@EqualsAndHashCode (of = "id")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    private String nombre;
    private String email;
    private String contrasena;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_perfil",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id")
    )
    private Set<Perfil>perfiles =  new HashSet<>();

    public Usuario(@NotBlank String nombre, @NotBlank @Email String email,Perfil perfil, String encodedPassword) {
        this.nombre = nombre;
        this.email = email;
        this.perfiles.add(perfil);
        this.contrasena = encodedPassword;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getPerfiles().stream()
                .map(perfil ->  new SimpleGrantedAuthority(perfil.getRol().name()))
        .toList();
    }

    @Override
    public String getPassword() {
        return getContrasena();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

