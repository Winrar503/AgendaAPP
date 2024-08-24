package org.darwin.servicios.implementaciones;


import org.darwin.modelos.Contacto;
import org.darwin.modelos.Nota;
import org.darwin.repositorios.INotaRepository;
import org.darwin.servicios.interfaces.INotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<Nota> obtenerPorContactoId(Integer id){
        return notaRepository.findByContactoId(id);
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

//    @Override
//    public void eliminarPorId(Integer id) {
//        Nota nota = notaRepository.findById(id).orElseThrow(() -> new RuntimeException("Nota no encontrada"));
//        nota.setEliminado(true);
//        notaRepository.save(nota);
//    }
//
//    public void eliminarDefinitivamentePorId(Integer id) {
//        notaRepository.deleteById(id);
//    }
//
//    @Override
//    public void restaurarPorId(Integer id) {
//        Nota nota = notaRepository.findById(id).orElseThrow(() -> new RuntimeException("nota no encontrado"));
//        nota.setEliminado(false);
//        notaRepository.save(nota);
//    }
}
