package com.weralf.foroChallenge.domain.usuario;

import jakarta.validation.constraints.Email;

public record DatosActualizarUsuario(@Email String email) {
}

