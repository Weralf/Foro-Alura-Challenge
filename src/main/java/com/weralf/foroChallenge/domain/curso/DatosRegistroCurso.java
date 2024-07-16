package com.weralf.foroChallenge.domain.curso;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroCurso(@NotBlank String nombre,
                                 @NotBlank String categoria) {
}
