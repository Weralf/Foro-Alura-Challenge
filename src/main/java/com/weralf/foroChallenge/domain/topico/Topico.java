package com.weralf.foroChallenge.domain.topico;

import com.weralf.foroChallenge.domain.curso.Curso;
import com.weralf.foroChallenge.domain.respuesta.DatosActualizarRespuesta;
import com.weralf.foroChallenge.domain.respuesta.Respuesta;
import com.weralf.foroChallenge.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topico")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private Boolean status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @OneToMany(mappedBy = "topico", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Respuesta> respuestas;
    private LocalDateTime fecha_creacion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    public Topico(String titulo, String mensaje, Curso curso, LocalDateTime fecha_creacion, Usuario autor) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.curso = curso;
        this.fecha_creacion = fecha_creacion;
        this.autor = autor;
        this.status = false;
    }

    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico) {
        if (datosActualizarTopico.titulo() != null) {
            this.titulo = datosActualizarTopico.titulo();
        }
        if (datosActualizarTopico.mensaje() != null) {
            this.mensaje = datosActualizarTopico.mensaje();
        }
        if (datosActualizarTopico.status() != null) {
            this.status = datosActualizarTopico.status();
        }
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Boolean getStatus() {
        return status;
    }

    public Curso getCurso() {
        return curso;
    }

    public LocalDateTime getFechaCreacion() {
        return fecha_creacion;
    }

    public Usuario getAutor() {
        return autor;
    }
}