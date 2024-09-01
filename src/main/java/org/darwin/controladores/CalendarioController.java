package org.darwin.controladores;

import org.darwin.modelos.Evento;
import org.darwin.servicios.interfaces.IContactoService;
import org.darwin.servicios.interfaces.IEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/calendario")
public class CalendarioController {

    @Autowired
    private IEventoService eventoService;

    @Autowired
    private IContactoService contactoService;

    @GetMapping
    public String verCalendario(Model model) {
        List<Evento> eventos = eventoService.obtenerTodosLosEventos();
        model.addAttribute("eventos", eventos);
        return "calendario/index";
    }


    @GetMapping("/crear")
    public String crearEvento(Model model) {
        model.addAttribute("evento", new Evento());
        model.addAttribute("contactos", contactoService.obtenerTodos());
        return "calendario/crear";
    }


    @PostMapping("/guardar")
    public String guardarEvento(@ModelAttribute Evento evento, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Ocurrió un error al guardar el evento.");
            return "redirect:/calendario/crear";
        }

        eventoService.guardarEvento(evento);
        redirectAttributes.addFlashAttribute("msg", "Evento guardado correctamente.");
        return "redirect:/calendario";
    }


    @GetMapping("/editar/{id}")
    public String editarEvento(@PathVariable("id") Integer id, Model model) {
        Evento evento = eventoService.obtenerEventoPorId(id);
        model.addAttribute("evento", evento);
        model.addAttribute("contactos", contactoService.obtenerTodos());
        return "calendario/editar";
    }

    @PostMapping("/actualizar")
    public String actualizarEvento(@ModelAttribute Evento evento, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Ocurrió un error al actualizar el evento.");
            return "redirect:/calendario/editar/" + evento.getId();
        }
        eventoService.guardarEvento(evento);
        redirectAttributes.addFlashAttribute("msg", "Evento actualizado correctamente.");
        return "redirect:/calendario";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarEvento(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        eventoService.eliminarEvento(id);
        redirectAttributes.addFlashAttribute("msg", "Evento eliminado correctamente.");
        return "redirect:/calendario";
    }

    @GetMapping("/detalles/{id}")
    public String verDetallesEvento(@PathVariable("id") Integer id, Model model) {
        Evento evento = eventoService.obtenerEventoPorId(id);
        model.addAttribute("evento", evento);
        return "calendario/detalles";
    }
}
