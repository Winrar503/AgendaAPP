package org.darwin.servicios.implementaciones;

import org.darwin.modelos.Evento;
import org.darwin.repositorios.IEventoRepository;
import org.darwin.servicios.interfaces.IEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventoService implements IEventoService {

    @Autowired
    private IEventoRepository eventoRepository;

    @Override
    public List<Evento> obtenerEventosPorContacto(Integer contactoId) {
        return eventoRepository.findByContactoId(contactoId);
    }

    @Override
    public Evento guardarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    @Override
    public void eliminarEvento(Integer id) {
        eventoRepository.deleteById(id);
    }

    @Override
    public List<Evento> obtenerTodosLosEventos() {
        return eventoRepository.findAll();
    }

    @Override
    public Evento obtenerEventoPorId(Integer id) {
        return eventoRepository.findById(id).orElse(null);
    }
}

