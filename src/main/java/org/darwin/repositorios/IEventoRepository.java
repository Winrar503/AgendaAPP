package org.darwin.repositorios;

import org.darwin.modelos.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IEventoRepository extends JpaRepository<Evento, Integer> {
    List<Evento> findByContactoId(Integer contactoId);
}
