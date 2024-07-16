package com.weralf.foroChallenge.domain.topico;

import com.weralf.foroChallenge.domain.curso.Curso;
import com.weralf.foroChallenge.domain.respuesta.Respuesta;
import com.weralf.foroChallenge.domain.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public record DatosListadoTopico(Long id, String titulo, String mensaje, String autor, String curso, LocalDateTime fechaCreacion, Boolean status) {
    public DatosListadoTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre(),
                topico.getFechaCreacion(),
                topico.getStatus()
        );
    }
}

