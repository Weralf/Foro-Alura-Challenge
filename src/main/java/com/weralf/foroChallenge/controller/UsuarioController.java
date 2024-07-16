package com.weralf.foroChallenge.controller;

import com.weralf.foroChallenge.domain.respuesta.DatosDetallesRespuesta;
import com.weralf.foroChallenge.domain.topico.*;
import com.weralf.foroChallenge.domain.usuario.*;
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
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datos, UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario = repository.save(new Usuario(datos.nombre(), datos.email(), service.hashPassword(datos.clave())));
        DatosDetallesUsuario datosDetallesUsuario = new DatosDetallesUsuario(usuario);

        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosDetallesUsuario);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuario>> listadoUsuario(@PageableDefault(sort = "nombre") Pageable paginacion) {
        return ResponseEntity.ok(repository.findAll(paginacion).map(DatosListadoUsuario::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetallesUsuario> datosUsuario(@PathVariable Long id){
        Usuario usuario = repository.getReferenceById(id);
        var datosUsuario = new DatosDetallesUsuario(usuario);
        return ResponseEntity.ok(datosUsuario);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarUsuario(@RequestBody @Valid DatosActualizarUsuario datos, @PathVariable Long id){
        return ResponseEntity.ok(service.actualizarUsuario(datos, id));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity borrarUsuario(@PathVariable Long id){
        service.borrarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
