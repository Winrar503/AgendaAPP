package org.darwin.repositorios;

import org.darwin.modelos.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ICategoriaRepository extends JpaRepository<Categoria, Integer> {
    List<Categoria> findByEliminado(boolean eliminado);
}
