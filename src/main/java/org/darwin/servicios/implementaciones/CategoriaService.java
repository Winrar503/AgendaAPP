package org.darwin.servicios.implementaciones;

import org.darwin.modelos.Categoria;
import org.darwin.repositorios.ICategoriaRepository;
import org.darwin.servicios.interfaces.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService implements ICategoriaService {
    @Autowired
    private ICategoriaRepository categoriaRepository;


    @Override
    public Page<Categoria> buscarTodosPaginados(Pageable pageable) {
        return categoriaRepository.findAll(pageable);
    }

    @Override
    public List<Categoria> obtenerTodos() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> buscarPorId(Integer id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Categoria crearOEditar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }


    public List<Categoria> findContactosEliminados() {
        return categoriaRepository.findByEliminado(true);
    }
    @Override
    public void eliminarPorId(Integer id) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria no encontrado"));
        categoria.setEliminado(true);
        categoriaRepository.save(categoria);
    }


    @Override
    public void restaurarContacto(Integer id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("categoria no encontrado"));
        categoria.setEliminado(false);
        categoriaRepository.save(categoria);
    }


    @Override
    public void eliminarDefinitivamenteContacto(Integer id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public Page<Categoria> buscarTodosNoEliminadosPaginados(Pageable pageable){
        return categoriaRepository.findByEliminadoFalse(pageable);
    }
//    servicio de papelra

//    @Override
//    public void eliminarPorId(Integer id) {
//        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
//        categoria.setEliminado(true);
//        categoriaRepository.save(categoria);
//    }
//
//    public void eliminarDefinitivamentePorId(Integer id) {
//        categoriaRepository.deleteById(id);
//    }
//
//    @Override
//    public void restaurarPorId(Integer id) {
//        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Contacto no encontrado"));
//        categoria.setEliminado(false);
//        categoriaRepository.save(categoria);
//    }



}