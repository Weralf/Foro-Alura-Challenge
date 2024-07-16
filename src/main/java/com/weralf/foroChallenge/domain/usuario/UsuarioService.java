package com.weralf.foroChallenge.domain.usuario;

import com.weralf.foroChallenge.domain.topico.DatosDetallesTopico;
import com.weralf.foroChallenge.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;

    public DatosDetallesUsuario actualizarUsuario(DatosActualizarUsuario datos, Long id) {
        if (!repository.findById(id).isPresent()) {
            throw new ValidacionDeIntegridad("El id del usuario no existe");
        }

        var usuario = repository.getReferenceById(id);

        usuario.actualizarDatos(datos);

        return new DatosDetallesUsuario(usuario);
    }

    public void borrarUsuario(Long id) {
        if (!repository.findById(id).isPresent()) {
            throw new ValidacionDeIntegridad("El id del usuario no existe");
        }

        repository.deleteById(id);
    }

    public String hashPassword(String clave) {
        String hashedPassword = BCrypt.hashpw(clave, BCrypt.gensalt());
        return hashedPassword;
    }
}
