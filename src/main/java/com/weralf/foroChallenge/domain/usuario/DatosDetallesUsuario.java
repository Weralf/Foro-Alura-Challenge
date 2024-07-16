package com.weralf.foroChallenge.domain.usuario;

public record DatosDetallesUsuario(String nombre, String email) {
    public DatosDetallesUsuario(Usuario usuario) {
        this(
                usuario.getNombre(), usuario.getEmail()
        );
    }
}
