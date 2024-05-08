package com.analista.proyecto.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.analista.proyecto.Model.Elemento;
import com.analista.proyecto.Model.FechaVisita;
import com.analista.proyecto.Model.Interno;
import com.analista.proyecto.Model.TipoVisita;
import com.analista.proyecto.Model.Visita;
import com.analista.proyecto.Service.IElementoService;
import com.analista.proyecto.Service.IFechaVisitaService;
import com.analista.proyecto.Service.IInternoService;
import com.analista.proyecto.Service.ITipoVisitaService;
import com.analista.proyecto.Service.IVisitaService;


import jakarta.validation.Valid;

@Controller
@SessionAttributes("fechaVisita")
public class detalleController {

    @Autowired
    IInternoService internoService;

    @Autowired
    IVisitaService visitaService;

    @Autowired
    IElementoService elementoService;

    @Autowired
    IFechaVisitaService fechaVisitaService;

    @Autowired
    ITipoVisitaService tipoVisitaService;

    @GetMapping("/interno/{id}/detalle")
    public String mostrarDetalle(@PathVariable Long id, Model model) {
        
        // Inicializar una nueva instancia de FechaVisita
        FechaVisita fechaVisita = new FechaVisita();
        model.addAttribute("fechaVisita", fechaVisita);
        model.addAttribute("tipoVisita", tipoVisitaService.buscarTodos());
        // Buscar el interno por su ID
        Interno interno = internoService.buscarPorId(id);

        Visita visita = visitaService.buscarPorId(id);

        if (interno != null) {
            // Obtener las visitas asociadas al interno
            List<Visita> visitas = visitaService.obtenerVisitasPorInterno(interno);

            // Obtener los elementos asociados al interno
            List<Elemento> elementos = elementoService.obtenerElementosPorInterno(interno);

            // Obtener la última fecha de visita
          

            // Agregar los datos al modelo
            model.addAttribute("interno", interno);
            model.addAttribute("visitas", visitas);
            model.addAttribute("elementos", elementos);

            // Retornar la vista detalle
            return "detalle";
        } else {
            // Redirigir a una página de error si el interno no existe
            return "redirect:/error";
        }

    }

    @PostMapping("/guardar-fecha/{id}")
    public String guardar(@PathVariable("id") Long visitaId, @Valid FechaVisita fechaVisita,
            RedirectAttributes msgFlash, SessionStatus status, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Corregir errores.");
            model.addAttribute("yourvisitaId", visitaId);
            return "redirect:/home";
        }

        // Obtener la visita por su ID
        Visita visita = visitaService.buscarPorId(visitaId);

        if (visita == null) {
            // Manejar el caso en que la visita no se encuentra
            msgFlash.addFlashAttribute("error", "No se encontró la visita.");
            return "redirect:/home";
        }

        try {
            // Crear una nueva instancia de FechaVisita
            FechaVisita nuevaFechaVisita = new FechaVisita();
            nuevaFechaVisita.setFecha(fechaVisita.getFecha());
            nuevaFechaVisita.setTipoVisita(fechaVisita.getTipoVisita());
            nuevaFechaVisita.setVisita(visita);

            // Guardar la nueva fecha de visita
            fechaVisitaService.guardar(nuevaFechaVisita);

            // Agregar la nueva fecha de visita a la visita
            visita.addFechaVisita(nuevaFechaVisita);
            visitaService.guardar(visita);

            msgFlash.addFlashAttribute("success", "Fecha de visita guardada correctamente.");
            status.setComplete();
            

        } catch (Exception e) {
            // Manejar cualquier excepción que pueda ocurrir durante el proceso de guardado
            msgFlash.addFlashAttribute("error", "Error al guardar la fecha de visita.");
            return "redirect:/home";
        }

        return "redirect:/home";
    }

     @ModelAttribute("tipoVisitas")
    public List<TipoVisita> getTipoVisitas(){
        return tipoVisitaService.buscarTodos();
    }


}
