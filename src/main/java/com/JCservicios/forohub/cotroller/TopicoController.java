package com.JCservicios.forohub.cotroller;

import com.JCservicios.forohub.domain.respuesta.*;
import com.JCservicios.forohub.domain.topico.*;
import com.JCservicios.forohub.domain.usuario.Usuario;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    private final TopicoService topicoService;
    private final RespuestaService respuestaService;

    public TopicoController(TopicoService topicoService, RespuestaService respuestaService) {
        this.topicoService = topicoService;
        this.respuestaService = respuestaService;
    }


    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> crearTopico(@RequestBody @Valid DatosRegistroTopico datos,
                                                            UriComponentsBuilder uriComponentsBuilder,
                                                            @AuthenticationPrincipal Usuario usuario
                                                            )
    {

        DatosRespuestaTopico respuesta = topicoService.crearTopico(datos, usuario.getId());

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

    @PreAuthorize("hasRole('ADMIN') or @topicoSecurity.isAutor(#id, authentication.principal.id)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {
        topicoService.eliminarTopico(id, usuario) ;
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/respuestas/{idRespuesta}/solucion")
    @PreAuthorize("hasRole('ADMIN') or @topicoSecurity.isAutor(#id, authentication.principal.id)")
    public ResponseEntity<DatosSolucion> solucionTopico( @PathVariable Long id, @PathVariable Long idRespuesta) {
          DatosSolucion solucion = topicoService.marcarComoSolucion(id, idRespuesta);
        return ResponseEntity.ok(solucion);
    }

    /** Request de respuestas **/

    @PostMapping("/{id}/respuestas") ResponseEntity agregarRespuesta(@PathVariable Long id, @RequestBody @Valid DatosRegistroRespuesta datos,
                                                                     @AuthenticationPrincipal Usuario usuario,
                                                                     UriComponentsBuilder uriComponentsBuilder) {
        DatosRepuesta datosRepuesta = respuestaService.agregarRespuesta(id, datos, usuario);

        var uri = uriComponentsBuilder.path("/topicos/{id}/respuestas/{idRespuesta}")
                .buildAndExpand(id, datosRepuesta.id())
                .toUri();

        return ResponseEntity.created(uri).body(datosRepuesta);
    }

    @PutMapping("/{idTopico}/respuestas/{idRespuesta}") ResponseEntity actualizarRespuesta(@PathVariable Long idTopico,
                                                                                          @PathVariable Long idRespuesta,
                                                                                           @AuthenticationPrincipal Usuario usuario,
                                                                                           @RequestBody @Valid DatosActualizarRespuesta datos ) {
        DatosRepuesta datosRepuesta = respuestaService.actualizarRespuesta(idTopico, idRespuesta, usuario, datos);
        return ResponseEntity.ok(datosRepuesta);

    }

    @GetMapping("/{idTopico}/respuestas")
    public ResponseEntity<Page<DatosRepuesta>> listarRespuestas(@PageableDefault(size = 10, sort = "fechaCreacion") Pageable pageable, @PathVariable Long idTopico){
        Page <DatosRepuesta> repuestas = respuestaService.listar(pageable, idTopico);
        return ResponseEntity.ok(repuestas);
    }

    @PreAuthorize( "hasRole('ADMIN') or @respuestaSecurity.isAutor(#idRespuesta, authentication.principal.id)")
    @DeleteMapping("/{idTopico}/respuestas/{idRespuesta}")
    public ResponseEntity<Void> eliminarRespuesta(@PathVariable Long idTopico, @PathVariable Long idRespuesta) {
        respuestaService.eliminarRespuesta(idRespuesta);
        return ResponseEntity.noContent().build();
    }



}
