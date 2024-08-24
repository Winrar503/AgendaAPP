package org.darwin.servicios.implementaciones;

import org.darwin.modelos.Contacto;
import org.darwin.repositorios.IContactoRepository;
import org.darwin.servicios.interfaces.IContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactoService implements IContactoService {
    @Autowired
    private IContactoRepository contactoRepository;

    @Override
    public Page<Contacto> buscarTodosPaginados(Pageable pageable) {
        return contactoRepository.findAll(pageable);
    }

    @Override
    public List<Contacto> obtenerTodos() {
        return contactoRepository.findAll();
    }

    @Override
    public Optional<Contacto> buscarPorId(Integer id) {
        return contactoRepository.findById(id);
    }

    @Override
    public Contacto crearOEditar(Contacto contacto) {
        return contactoRepository.save(contacto);
    }


//    @Override
//    public void eliminarPorId(Integer id) {
//        contactoRepository.deleteById(id);
//    }

    public List<Contacto> findContactosEliminados() {
        return contactoRepository.findByEliminado(true);
    }

    @Override
    public void eliminarPorId(Integer id) {
        Contacto contacto = contactoRepository.findById(id).orElseThrow(() -> new RuntimeException("Contacto no encontrado"));
        contacto.setEliminado(true);
        contactoRepository.save(contacto);
    }
    @Override
    public void restaurarContacto(Integer id) {
        Contacto contacto = contactoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contacto no encontrado"));
        contacto.setEliminado(false);
        contactoRepository.save(contacto);
    }


    @Override
    public void eliminarDefinitivamenteContacto(Integer id) {
        contactoRepository.deleteById(id);
    }
//
//    public void eliminarDefinitivamentePorId(Integer id) {
//        contactoRepository.deleteById(id);
//    }

//    @Override
//    public void restaurarPorId(Integer id) {
//        Contacto contacto = contactoRepository.findById(id).orElseThrow(() -> new RuntimeException("Contacto no encontrado"));
//        contacto.setEliminado(false);
//        contactoRepository.save(contacto);
//    }


}
