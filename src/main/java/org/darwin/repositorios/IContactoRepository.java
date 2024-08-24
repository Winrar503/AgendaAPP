package org.darwin.repositorios;

import org.darwin.modelos.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IContactoRepository extends JpaRepository<Contacto, Integer> {
    List<Contacto> findByEliminado(boolean eliminado);
}
