package org.darwin.controladores;


import org.darwin.modelos.Nota;
import org.darwin.servicios.interfaces.ICategoriaService;
import org.darwin.servicios.interfaces.INotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/notas")
public class NotaController {
    @Autowired
    private INotaService notaService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1) - 1; // si no está seteado se asigna 0
        int pageSize = size.orElse(5); // tamaño de la página, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Nota> notas = notaService.buscarTodosPaginados(pageable);
        model.addAttribute("notas", notas);

        int totalPages = notas.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "nota/index";
    }

    @GetMapping("/create")
    public String create(Nota nota) {
        return "nota/create"; // Asegúrate de que la vista está en la ubicación correcta
    }

    @PostMapping("/save")
    public String save(Nota nota, BindingResult result, Model model, RedirectAttributes attributes) {
        if(result.hasErrors()) {
            model.addAttribute(nota);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "nota/create";
        }

        notaService.crearOEditar(nota);
        attributes.addFlashAttribute("msg", "Nota creada correctamente");
        return "redirect:/notas"; // Redirige a la lista de contactos
    }


        @GetMapping("/details/{id}")
        public String details(@PathVariable("id") Integer id, Model model){
            Nota nota = notaService.buscarPorId(id).get();
            model.addAttribute("nota", nota);
            return "nota/details";
        }

        @GetMapping("/edit/{id}")
        public String edit(@PathVariable("id") Integer id, Model model){
            Nota nota = notaService.buscarPorId(id).get();
            model.addAttribute("nota", nota);
            return "nota/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        Nota nota = notaService.buscarPorId(id).get();
        model.addAttribute("categoria", nota);
        return "nota/delete";
    }

    @PostMapping("/delete")
    public String delete(Nota nota, RedirectAttributes attributes){
        notaService.eliminarPorId(nota.getId());
        attributes.addFlashAttribute("msg", "Nota eliminada correctamente");
        return "redirect:/notas";
    }
}
