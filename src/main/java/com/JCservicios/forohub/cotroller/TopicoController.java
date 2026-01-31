package com.JCservicios.forohub.cotroller;

import com.JCservicios.forohub.domain.topico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }


    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> crearTopico(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder) {

        DatosRespuestaTopico respuesta = topicoService.crearTopico(datos);

        var uri = uriComponentsBuilder.path("/Topicos/{id}").buildAndExpand(respuesta.id()).toUri();


        return ResponseEntity.created(uri).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleTopico>> ListarTopicos(@PageableDefault(size = 10, sort = "titulo") Pageable pageable) {

        Page<DatosDetalleTopico> topicos =  topicoService.listar(pageable);

        return ResponseEntity.ok(topicos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> ObtenerTopico(@PathVariable Long id) {

        DatosDetalleTopico topico = topicoService.obtenerTopico(id);
        return ResponseEntity.ok(topico);

    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> actualizarTopico(@RequestBody @Valid DatosRegistroTopico datos, @PathVariable Long id) {
        DatosDetalleTopico topicoActualizado = topicoService.actualizarTopico(datos,id);
        return ResponseEntity.ok(topicoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
