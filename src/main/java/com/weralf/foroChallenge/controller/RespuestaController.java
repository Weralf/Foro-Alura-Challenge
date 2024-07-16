package com.weralf.foroChallenge.controller;

import com.weralf.foroChallenge.domain.respuesta.*;
import com.weralf.foroChallenge.domain.topico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private RespuestaRepository repository;

    @Autowired
    private RespuestaService service;

    @PostMapping
    @Transactional
    public ResponseEntity registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datos){
        var response = service.crearRespuesta(datos);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoRespuesta>> listadoRespuestas(@PageableDefault(sort = "mensaje") Pageable paginacion) {
        return ResponseEntity.ok(repository.findAll(paginacion).map(DatosListadoRespuesta::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetallesRespuesta> datosRespuesta(@PathVariable Long id){
        Respuesta respuesta = repository.getReferenceById(id);
        var datosRespuesta = new DatosDetallesRespuesta(respuesta);
        return ResponseEntity.ok(datosRespuesta);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarRespuesta(@RequestBody @Valid DatosActualizarRespuesta datos, @PathVariable Long id){
        return ResponseEntity.ok(service.actualizarRespuesta(datos, id));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity borrarRespuesta(@PathVariable Long id){
        service.borrarRespuesta(id);
        return ResponseEntity.noContent().build();
    }

}