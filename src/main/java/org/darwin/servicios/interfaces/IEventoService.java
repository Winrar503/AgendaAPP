package org.darwin.servicios.interfaces;

import org.darwin.modelos.Evento;
import java.util.List;

public interface IEventoService {
    List<Evento> obtenerEventosPorContacto(Integer contactoId);

    Evento guardarEvento(Evento evento);

    void eliminarEvento(Integer id);

    List<Evento> obtenerTodosLosEventos();  // Método adicional para obtener todos los eventos

    Evento obtenerEventoPorId(Integer id);  // Método para obtener un evento por su ID
}

