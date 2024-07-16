package com.weralf.foroChallenge.domain.respuesta;

import com.weralf.foroChallenge.domain.topico.DatosDetallesTopico;
import com.weralf.foroChallenge.domain.topico.Topico;
import com.weralf.foroChallenge.domain.topico.TopicoRepository;
import com.weralf.foroChallenge.domain.usuario.UsuarioRepository;
import com.weralf.foroChallenge.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RespuestaService {

    @Autowired
    RespuestaRepository repository;

    @Autowired
    TopicoRepository topicoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;


    public DatosDetallesRespuesta crearRespuesta(DatosRegistroRespuesta datos){
        if (!topicoRepository.findById(datos.idTopico()).isPresent()){
            throw new ValidacionDeIntegridad("El id del topico no existe");
        }
        if (!usuarioRepository.findById(datos.idUsuario()).isPresent()){
            throw new ValidacionDeIntegridad("El id del autor no existe");
        }

        var topico = topicoRepository.findById(datos.idTopico()).get();

        var autor = usuarioRepository.findById(datos.idUsuario()).get();

        LocalDateTime fecha = LocalDateTime.now();

        var respuesta = new Respuesta(datos.mensaje(), topico, fecha, autor);

        repository.save(respuesta);

        return new DatosDetallesRespuesta(respuesta);
    }

    public DatosDetallesRespuesta actualizarRespuesta(DatosActualizarRespuesta datos, Long id){
        if (!repository.findById(id).isPresent()){
            throw new ValidacionDeIntegridad("El id de la respuesta no existe");
        }

        var respuesta = repository.findById(id).get();

        respuesta.actualizarDatos(datos);

        return new DatosDetallesRespuesta(respuesta);
    }

    public void borrarRespuesta(Long id){
        if (!repository.findById(id).isPresent()){
            throw new ValidacionDeIntegridad("El id de la respuesta no existe");
        }

        repository.deleteById(id);
    }
}
