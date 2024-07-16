package com.weralf.foroChallenge.domain.respuesta;

import java.time.LocalDateTime;

public record DatosDetallesRespuesta(String mensaje, String topico, LocalDateTime fecha, String autor, Boolean solucion) {
    public DatosDetallesRespuesta(Respuesta respuesta) {
        this(respuesta.getMensaje(), respuesta.getTopico().getTitulo(), respuesta.getFechaCreacion(),
                respuesta.getAutor().getNombre(), respuesta.getSolucion());
    }
}
