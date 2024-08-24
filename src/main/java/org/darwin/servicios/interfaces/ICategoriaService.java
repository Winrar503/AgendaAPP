package org.darwin.servicios.interfaces;

import org.darwin.modelos.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICategoriaService {
    Page<Categoria> buscarTodosPaginados(Pageable pageable);

    List<Categoria> obtenerTodos();

    Optional<Categoria> buscarPorId(Integer id);

    Categoria crearOEditar(Categoria categoria);

    void eliminarPorId(Integer id);

//    void eliminarDefinitivamentePorId(Integer id);  // Nuevo método
//
//    void restaurarPorId(Integer id);  // Nuevo método

    List<Categoria> findContactosEliminados();

    void restaurarContacto(Integer id);

    void eliminarDefinitivamenteContacto(Integer id);
}
