package com.JCservicios.forohub.cotroller;


import com.JCservicios.forohub.domain.curso.CursoService;
import com.JCservicios.forohub.domain.curso.DatosRegistroCurso;
import com.JCservicios.forohub.domain.curso.DatosRespuestaCurso;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaCurso>> listarCursos(@PageableDefault(size = 10, sort = {"categoria","nombre"})Pageable paginacion) {
        Page<DatosRespuestaCurso> cursos = cursoService.listar(paginacion);
        return ResponseEntity.ok(cursos);
    }

}
