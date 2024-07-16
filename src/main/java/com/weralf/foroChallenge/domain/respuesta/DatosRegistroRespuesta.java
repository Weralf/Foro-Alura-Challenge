package com.weralf.foroChallenge.domain.respuesta;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroRespuesta(@NotBlank String mensaje,
                                     @NotNull Long idTopico,
                                     @NotNull Long idUsuario) {
}
