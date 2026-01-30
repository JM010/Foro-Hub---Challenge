package com.JCservicios.forohub.cotroller;

import com.JCservicios.forohub.domain.usuario.*;
import com.JCservicios.forohub.infra.security.DatosJWT;
import com.JCservicios.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final TokenService tokenService;

    private final AuthService authService;

    private final AuthenticationManager manager;


    public AuthenticationController(TokenService tokenService, AuthService authService, AuthenticationManager manager) {
        this.tokenService = tokenService;
        this.authService = authService;
        this.manager = manager;
    }


    @PostMapping("/register")
    public ResponseEntity<RegistroResponse> register(@RequestBody @Valid DTORegistro datosRegistro) {
        RegistroResponse response = authService.registrarUsuario(datosRegistro);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<DatosJWT> login( @RequestBody @Valid DatosAutenticacion datos) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(datos.email(), datos.password());
        var authenticated = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generarToken((Usuario) authenticated.getPrincipal());

        return ResponseEntity.ok(new DatosJWT(tokenJWT));

    }
}
