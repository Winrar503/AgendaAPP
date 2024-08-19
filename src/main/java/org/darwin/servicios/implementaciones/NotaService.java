package org.darwin.servicios.implementaciones;


import org.darwin.modelos.Nota;
import org.darwin.repositorios.INotaRepository;
import org.darwin.servicios.interfaces.INotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService implements INotaService {
    @Autowired
        private INotaRepository notaRepository;

    @Override
    public Page<Nota> buscarTodosPaginados(Pageable pageable) {
        return notaRepository.findAll(pageable);
    }

    @Override
    public List<Nota> obtenerTodos() {
        return notaRepository.findAll();
    }

    @Override
    public Optional<Nota> buscarPorId(Integer id) {
        return notaRepository.findById(id);
    }

    @Override
    public Nota crearOEditar(Nota nota) {
        return notaRepository.save(nota);
    }

    @Override
    public void eliminarPorId(Integer id) {
        notaRepository.deleteById(id);
    }
}
