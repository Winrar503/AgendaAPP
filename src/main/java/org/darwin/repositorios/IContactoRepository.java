package org.darwin.repositorios;

import org.darwin.modelos.Contacto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IContactoRepository extends JpaRepository<Contacto, Integer> {
    Page<Contacto> findByEliminadoFalse(Pageable pageable);

    List<Contacto> findByEliminado(boolean eliminado);
}
