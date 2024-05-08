// package com.analista.proyecto.Controller;


// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.validation.BindingResult;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;

// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.SessionAttributes;
// import org.springframework.web.bind.support.SessionStatus;

// import org.springframework.web.servlet.mvc.support.RedirectAttributes;


// import com.analista.proyecto.Model.FechaVisita;
// import com.analista.proyecto.Model.Visita;
// import com.analista.proyecto.Service.IFechaVisitaService;
// import com.analista.proyecto.Service.IVisitaService;
// import jakarta.validation.Valid;

// @Controller
// @SessionAttributes("fechaVisita")
// public class fechaController {

//     @Autowired
//     private IVisitaService visitaService;

//     @Autowired
//     private IFechaVisitaService fechaVisitaService;

//     @GetMapping("/agregarFecha")
//     public String mostrarFormularioNuevaFecha(@RequestParam("id") Long visitaId, Model model) {
//         Visita visita = visitaService.buscarPorId(visitaId);
//         model.addAttribute("visitas", visitaService.buscarTodo());
//         model.addAttribute("fechaVisitas", fechaVisitaService.buscarTodos());
//         model.addAttribute("nuevaFecha", new FechaVisita());
//         return "interno/ + {id} + /detalle"; 
//     }

 

// }
