package com.weralf.foroChallenge.domain.topico;

import com.weralf.foroChallenge.domain.respuesta.Respuesta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Boolean existsByTitulo(String titulo);

    Boolean existsByMensaje(String mensaje);

}