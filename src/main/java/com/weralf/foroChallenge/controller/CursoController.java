package com.weralf.foroChallenge.controller;

import com.weralf.foroChallenge.domain.curso.*;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    CursoService service;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrarCurso(@RequestBody @Valid DatosRegistroCurso datos, UriComponentsBuilder uriComponentsBuilder){
        Curso curso = cursoRepository.save(new Curso(datos));
        DatosDetallesCurso datosDetallesCurso = new DatosDetallesCurso(curso);

        URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(datosDetallesCurso);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoCurso>> listaCursos(@PageableDefault(sort = "nombre") Pageable paginacion) {
        return ResponseEntity.ok(cursoRepository.findAll(paginacion).map(DatosListadoCurso::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetallesCurso> datosCurso(@PathVariable Long id){
        Curso curso = cursoRepository.getReferenceById(id);
        var datosCurso = new DatosDetallesCurso(curso);
        return ResponseEntity.ok(datosCurso);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarCurso(@RequestBody @Valid DatosActualizarCurso datos, @PathVariable Long id){
        return ResponseEntity.ok(service.actualizarCurso(datos, id));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity borrarCurso(@PathVariable Long id){
        service.borrarCurso(id);
        return ResponseEntity.noContent().build();
    }

}
