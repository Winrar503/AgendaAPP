package org.darwin.repositorios;

import org.darwin.modelos.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ICategoriaRepository extends JpaRepository<Categoria, Integer> {
}
