package com.weralf.foroChallenge.domain.topico.validaciones;

import com.weralf.foroChallenge.domain.curso.CursoRepository;
import com.weralf.foroChallenge.domain.topico.DatosRegistroTopico;
import com.weralf.foroChallenge.domain.topico.TopicoRepository;
import com.weralf.foroChallenge.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicosDuplicados implements ValidadorDeTopicos{

    @Autowired
    TopicoRepository topicoRepository;

    @Override
    public void validar(DatosRegistroTopico datos) {

        if (datos.titulo() == null){
            return;
        }

        if (datos.mensaje() == null){
            return;
        }

        var titulo = topicoRepository.existsByTitulo(datos.titulo());

        var mensaje = topicoRepository.existsByMensaje(datos.mensaje());

        if (titulo && mensaje){
            throw new ValidationException("No se permiten topicos duplicados");
        }
    }
}
