package org.darwin.controladores;

import org.darwin.modelos.Contacto;
import org.darwin.modelos.Nota;
import org.darwin.servicios.implementaciones.CategoriaService;
import org.darwin.servicios.interfaces.IContactoService;
import org.darwin.servicios.interfaces.INotaService;
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
@RequestMapping("/contactos")
public class ContactoController {
    @Autowired
    private IContactoService contactoService;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private INotaService notaService;


    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1) - 1; // si no está seteado se asigna 0
        int pageSize = size.orElse(5); // tamaño de la página, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Contacto> contactos = contactoService.buscarTodosPaginados(pageable);
        model.addAttribute("contactos", contactos);

        int totalPages = contactos.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "contacto/index";
    }


    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("contacto", new Contacto());
        model.addAttribute("categorias", categoriaService.obtenerTodos());
        return "contacto/create";
    }


        @PostMapping("/save")
    public String save(Contacto contacto, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            model.addAttribute(contacto);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "contacto/create";
        }

        contactoService.crearOEditar(contacto);
        attributes.addFlashAttribute("msg", "Contacto creado correctamente");
        return "redirect:/contactos";
    }

    //Controlador modificado temporalmente, si funciona lo dejo asi xd
    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model){
        Contacto contacto = contactoService.buscarPorId(id).get();
        List<Nota> notas = notaService.obtenerPorContactoId(contacto.getId());
        model.addAttribute("contacto", contacto);
        model.addAttribute("categorias", categoriaService.obtenerTodos());
        model.addAttribute("notas", notas);
        return "contacto/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Contacto contacto = contactoService.buscarPorId(id).get();
        model.addAttribute("contacto", contacto);
        model.addAttribute("categorias", categoriaService.obtenerTodos());
        return "contacto/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        Contacto contacto = contactoService.buscarPorId(id).get();
        model.addAttribute("contacto", contacto);
        return "contacto/delete";
    }

    @PostMapping("/delete")
    public String delete(Contacto contacto, RedirectAttributes attributes){
        contactoService.eliminarPorId(contacto.getId());
        attributes.addFlashAttribute("msg", "contacto eliminado correctamente");
        return "redirect:/contactos";
    }

//    controlador para papelera
//    @GetMapping("/papelera")
//    public String papelera(Model model) {
//        List<Contacto> contactosEliminados = contactoService.obtenerTodos().stream()
//                .filter(Contacto::isEliminado)
//                .collect(Collectors.toList());
//        model.addAttribute("contactosEliminados", contactosEliminados);
//        return "contacto/papelera";
//    }

    @GetMapping("/papelera")
    public String verPapelera(Model model) {
        List<Contacto> contactosEliminados = contactoService.findContactosEliminados();
        model.addAttribute("contactosEliminados", contactosEliminados);
        return "contacto/papelera";
    }


    @GetMapping("/restaurar/{id}")
    public String restaurarContacto(@PathVariable Integer id) {
        contactoService.restaurarContacto(id);
        return "redirect:/contactos/papelera";
    }

    @GetMapping("/eliminar-definitivamente/{id}")
    public String eliminarDefinitivamenteContacto(@PathVariable Integer id) {
        contactoService.eliminarDefinitivamenteContacto(id);
        return "redirect:/contactos/papelera";
    }


//    @PostMapping("/restore/{id}")
//    public String restaurar(@PathVariable("id") Integer id, RedirectAttributes attributes) {
//        Contacto contacto = contactoService.buscarPorId(id).orElseThrow(() -> new RuntimeException("Contacto no encontrado"));
//        contacto.setEliminado(false);
//        contactoService.crearOEditar(contacto);
//        attributes.addFlashAttribute("msg", "Contacto restaurado correctamente");
//        return "redirect:/contactos";
//    }
//
//    @PostMapping("/eliminar-definitivo/{id}")
//    public String eliminarDefinitivo(@PathVariable("id") Integer id, RedirectAttributes attributes) {
//        contactoService.eliminarDefinitivamentePorId(id);
//        attributes.addFlashAttribute("msg", "Contacto eliminado definitivamente");
//        return "redirect:/contactos/papelera";
//    }

}