package com.weralf.foroChallenge.domain.curso;

public record DatosDetallesCurso(String nombre, String categoria) {
    public DatosDetallesCurso(Curso curso) {
        this(curso.getNombre(), curso.getCategoria());
    }
}
