package org.darwin.controladores;

import org.darwin.modelos.Categoria;
import org.darwin.modelos.Contacto;
import org.darwin.servicios.interfaces.ICategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1) - 1; // si no está seteado se asigna 0
        int pageSize = size.orElse(5); // tamaño de la página, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Categoria> categorias = categoriaService.buscarTodosNoEliminadosPaginados(pageable);

        model.addAttribute("categorias", categorias);

        int totalPages = categorias.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "categoria/index";
    }

    @GetMapping("/create")
    public String create(Categoria categoria) {
        return "categoria/create"; // Asegúrate de que la vista está en la ubicación correcta
    }

    @PostMapping("/save")
    public String save(Categoria categoria, BindingResult result, Model model, RedirectAttributes attributes) {
        if(result.hasErrors()) {
            model.addAttribute(categoria);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "contacto/create";
        }

        categoriaService.crearOEditar(categoria);
        attributes.addFlashAttribute("msg", "Contacto creado correctamente");
        return "redirect:/categorias"; // Redirige a la lista de contactos
    }


    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model){
        Categoria categoria = categoriaService.buscarPorId(id).get();
        model.addAttribute("categoria", categoria);
        return "categoria/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Categoria categoria = categoriaService.buscarPorId(id).get();
        model.addAttribute("categoria", categoria);
        return "categoria/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        Categoria categoria = categoriaService.buscarPorId(id).get();
        model.addAttribute("categoria", categoria);
        return "categoria/delete";
    }

    @PostMapping("/delete")
    public String delete(Categoria categoria, RedirectAttributes attributes){
        categoriaService.eliminarPorId(categoria.getId());
        attributes.addFlashAttribute("msg", "categoria eliminado correctamente");
        return "redirect:/categorias";
    }


//    //    controlador para papelera
//    @GetMapping("/papelera")
//    public String papelera(Model model) {
//        List<Categoria> categoriasEliminados = categoriaService.obtenerTodos().stream()
//                .filter(Categoria::isEliminado)
//                .collect(Collectors.toList());
//        model.addAttribute("categoriasEliminados", categoriasEliminados);
//        return "categoria/papelera";
//    }

    @GetMapping("/papelera")
    public String verPapelera(Model model) {
        List<Categoria> categoriasEliminados = categoriaService.findContactosEliminados();
        model.addAttribute("categoriasEliminados", categoriasEliminados);
        return "categoria/papelera";
    }


    @GetMapping("/restaurar/{id}")
    public String restaurarContacto(@PathVariable Integer id) {
        categoriaService.restaurarContacto(id);
        return "redirect:/categorias/papelera";
    }

    @GetMapping("/eliminar-definitivamente/{id}")
    public String eliminarDefinitivamenteContacto(@PathVariable Integer id) {
        categoriaService.eliminarDefinitivamenteContacto(id);
        return "redirect:/categorias/papelera";
    }

    
//    @PostMapping("/restore/{id}")
//    public String restaurar(@PathVariable("id") Integer id, RedirectAttributes attributes) {
//        Categoria categoria = categoriaService.buscarPorId(id).orElseThrow(() -> new RuntimeException("categoria no encontrado"));
//        categoria.setEliminado(false);
//        categoriaService.crearOEditar(categoria);
//        attributes.addFlashAttribute("msg", "categoria restaurado correctamente");
//        return "redirect:/categoria";
//    }
//
//    @PostMapping("/eliminar-definitivo/{id}")
//    public String eliminarDefinitivo(@PathVariable("id") Integer id, RedirectAttributes attributes) {
//        categoriaService.eliminarDefinitivamentePorId(id);
//        attributes.addFlashAttribute("msg", "categoria eliminado definitivamente");
//        return "redirect:/categorias/papelera";
//    }
}