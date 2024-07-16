package com.weralf.foroChallenge.domain.curso;

import com.weralf.foroChallenge.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {
    @Autowired
    CursoRepository repository;

    public DatosDetallesCurso actualizarCurso(DatosActualizarCurso datos, Long id){
        if(!repository.findById(id).isPresent()){
            throw new ValidacionDeIntegridad("El id del curso no existe");
        }

        var curso = repository.getReferenceById(id);

        curso.actualizarDatos(datos);

        return new DatosDetallesCurso(curso);
    }

    public void borrarCurso(Long id){
        if (!repository.findById(id).isPresent()){
            throw new ValidacionDeIntegridad("El id del curso no existe");
        }

        repository.deleteById(id);
    }
}
