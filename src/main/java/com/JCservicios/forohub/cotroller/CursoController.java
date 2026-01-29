package com.JCservicios.forohub.cotroller;


import com.JCservicios.forohub.domain.curso.CursoService;
import com.JCservicios.forohub.domain.curso.DatosRegistroCurso;
import com.JCservicios.forohub.domain.curso.DatosRespuestaCurso;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/curso")
public class CursoController {


    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaCurso> registrarCurso(@RequestBody @Valid DatosRegistroCurso datos, UriComponentsBuilder uriComponentsBuilder) {

        DatosRespuestaCurso cursoCreado = cursoService.crearCurso(datos);

        var uri = uriComponentsBuilder.path("/curso/{id}").buildAndExpand(cursoCreado.id()).toUri();

        return ResponseEntity.created(uri).body(cursoCreado);
    }

}
