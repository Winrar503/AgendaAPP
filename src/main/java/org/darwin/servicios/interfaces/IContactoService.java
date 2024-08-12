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
}