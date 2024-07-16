package com.weralf.foroChallenge.domain.respuesta;

import java.time.LocalDateTime;

public record DatosListadoRespuesta(Long id, String mensaje, String topico, LocalDateTime fechaCreacion, String autor, Boolean solucion) {

    public DatosListadoRespuesta(Respuesta respuesta){
        this(
                respuesta.getId(), respuesta.getMensaje(), respuesta.getTopico().getTitulo(),
                respuesta.getFechaCreacion(), respuesta.getAutor().getNombre(), respuesta.getSolucion()
        );
    }
}
