package org.darwin.servicios.interfaces;

import org.darwin.modelos.Nota;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface INotaService {
    Page<Nota> buscarTodosPaginados(Pageable pageable);

    List<Nota> obtenerTodos();

    List<Nota> obtenerPorContactoId(Integer id);

    Optional<Nota> buscarPorId(Integer id);

    Nota crearOEditar(Nota nota);

    void eliminarPorId(Integer id);

//    void eliminarDefinitivamentePorId(Integer id);  // Nuevo método
//
//    void restaurarPorId(Integer id);  // Nuevo método
}
