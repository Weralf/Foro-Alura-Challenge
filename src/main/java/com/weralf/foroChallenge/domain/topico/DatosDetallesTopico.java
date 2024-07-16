package com.weralf.foroChallenge.domain.topico;

import com.weralf.foroChallenge.domain.respuesta.Respuesta;
import com.weralf.foroChallenge.domain.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public record DatosDetallesTopico(String titulo, String mensaje, LocalDateTime fechaCreacion, Boolean status, String autor, String curso) {
    public DatosDetallesTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),topico.getStatus(),
                topico.getAutor().getNombre(), topico.getCurso().getNombre());
    }
}
