package org.darwin.repositorios;

import org.darwin.modelos.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface INotaRepository extends JpaRepository<Nota, Integer> {
    List<Nota> findByContactoId(Integer id);
}
