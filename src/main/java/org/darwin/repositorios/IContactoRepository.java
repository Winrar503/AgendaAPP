package org.darwin.repositorios;

import org.darwin.modelos.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContactoRepository extends JpaRepository<Contacto, Integer> {
}
