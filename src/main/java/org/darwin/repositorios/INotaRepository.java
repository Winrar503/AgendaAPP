package org.darwin.repositorios;

import org.darwin.modelos.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INotaRepository extends JpaRepository<Nota, Integer> {
}
