package com.weralf.foroChallenge.domain.usuario;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroUsuario(@NotBlank String nombre,
                                  @NotBlank @Email String email,
                                  @NotBlank String clave) {
}