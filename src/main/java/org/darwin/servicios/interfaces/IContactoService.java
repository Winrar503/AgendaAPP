package org.darwin.servicios.interfaces;

import org.darwin.modelos.Contacto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IContactoService {
    Page<Contacto> buscarTodosPaginados(Pageable pageable);

    List<Contacto> obtenerTodos();

    Optional<Contacto> buscarPorId(Integer id);

    Contacto crearOEditar(Contacto contacto);

    void eliminarPorId(Integer id);

    List<Contacto> findContactosEliminados();

    void restaurarContacto(Integer id);

    void eliminarDefinitivamenteContacto(Integer id);

    Page<Contacto> buscarTodosNoEliminadosPaginados(Pageable pageable);

    Contacto obtenerContactoPorId(Integer id);  // Asegúrate de que este método esté declarado


}