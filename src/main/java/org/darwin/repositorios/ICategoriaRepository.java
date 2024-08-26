package org.darwin.repositorios;

import org.darwin.modelos.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ICategoriaRepository extends JpaRepository<Categoria, Integer> {
    Page<Categoria> findByEliminadoFalse(Pageable pageable);

    List<Categoria> findByEliminado(boolean eliminado);
}
