package com.analista.proyecto.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.analista.proyecto.Model.Interno;
import com.analista.proyecto.Model.Visita;
import com.analista.proyecto.Service.IInternoService;
import com.analista.proyecto.Service.IVisitaService;

@Controller
@RequestMapping("/calendario")
public class CalendarioController {
    @Autowired
    private IVisitaService visitaService;

    @Autowired
    IInternoService internoService;

    @GetMapping("/{id}")
    public String mostrarCalendario(@PathVariable Long id, Model model) {

        Interno interno = internoService.buscarPorId(id);
        List<Visita> visitas = visitaService.obtenerVisitasPorInterno(interno);
        model.addAttribute("visitas", visitas);
        return "calendario";
    }
}