package com.weralf.foroChallenge.domain.topico;

import com.weralf.foroChallenge.domain.curso.Curso;
import com.weralf.foroChallenge.domain.curso.CursoRepository;
import com.weralf.foroChallenge.domain.topico.validaciones.ValidadorDeTopicos;
import com.weralf.foroChallenge.domain.usuario.Usuario;
import com.weralf.foroChallenge.domain.usuario.UsuarioRepository;
import com.weralf.foroChallenge.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    List<ValidadorDeTopicos> validadorDeTopicos;

    public DatosDetallesTopico crearTopico(DatosRegistroTopico datos) {
        if (!usuarioRepository.findById(datos.idAutor()).isPresent()) {
            throw new ValidacionDeIntegridad("El id del autor no existe");
        }

        if (!cursoRepository.existsById(datos.idCurso())) {
            throw new ValidacionDeIntegridad("El id del curso no existe");
        }

        validadorDeTopicos.forEach(v -> v.validar(datos));

        var curso = cursoRepository.findById(datos.idCurso()).get();

        var autor = usuarioRepository.findById(datos.idAutor()).get();

        LocalDateTime fecha = LocalDateTime.now();

        var topico = new Topico(datos.titulo(), datos.mensaje(), curso, fecha, autor);

        topicoRepository.save(topico);

        return new DatosDetallesTopico(topico);
    }

    public DatosDetallesTopico actualizarTopico(DatosActualizarTopico datos, Long id){
        if (!topicoRepository.findById(id).isPresent()) {
            throw new ValidacionDeIntegridad("El id del topico no existe");
        }

        validadorDeTopicos.forEach(v -> v.validar(new DatosRegistroTopico(datos.titulo(), datos.mensaje(), null, null)));
        var topico = topicoRepository.getReferenceById(id);

        topico.actualizarDatos(datos);

        return new DatosDetallesTopico(topico);
    }

    public void borrarTopico(Long id){
        if (!topicoRepository.findById(id).isPresent()) {
            throw new ValidacionDeIntegridad("El id del topico no existe");
        }

        topicoRepository.deleteById(id);
    }
}
