package com.analista.proyecto.Controller;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.analista.proyecto.Model.Interno;
import com.analista.proyecto.Model.Modulo;
import com.analista.proyecto.Model.Motivo;
import com.analista.proyecto.Service.IInternoService;
import com.analista.proyecto.Service.IModuloService;
import com.analista.proyecto.Service.IMotivoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/internos")
@SessionAttributes("interno")
public class internoController {

    @Autowired
    IInternoService internoService;

    @Autowired
    IModuloService moduloService;

    @Autowired
    IMotivoService motivoService;

    @GetMapping("/nuevo")
    public String nuevo(Model model) {

        model.addAttribute("titulo", "CARGAR INTERNO");
        model.addAttribute("interno", new Interno());
        model.addAttribute("modulos", moduloService.buscarTodos());
        model.addAttribute("motivos", motivoService.buscarTodos());

        return "internos/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Interno interno, BindingResult result, Model model,
            @RequestParam("modulo") Long idModulo, RedirectAttributes msgFlash,
            SessionStatus status) {

        if (result.hasErrors()) {
            model.addAttribute("danger", "Corrija los Errores...");
            return "internos/form";
        }

        try {
            Interno internoGuardado = internoService.guardar(interno);

            Long internoId = internoGuardado.getId();
            String internoIdStr = String.valueOf(internoId);

            // Crear la carpeta 'documentos' en la raíz
            String carpetaDocumentosPath = Paths.get("documentos").toAbsolutePath().toString();
            File carpetaDocumentos = new File(carpetaDocumentosPath);
            if (!carpetaDocumentos.exists()) {
                carpetaDocumentos.mkdir();
            }

            // Crear la subcarpeta con el nombre y apellido del interno y su id
            String nombreApellido = interno.getNombreApellido();
            String subcarpetaPath = Paths.get(carpetaDocumentosPath, nombreApellido + "-" + interno.getId()).toString();
            File subcarpeta = new File(subcarpetaPath);
            if (!subcarpeta.exists()) {
                subcarpeta.mkdir();
            }

            internoService.guardar(interno);
            msgFlash.addFlashAttribute("success", "Interno Guardado Correctamente.");
            status.setComplete();

            return "redirect:/home";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("danger", "Ya existe un interno cargado con el DNI N: " + interno.getDni());
            return "internos/form";
        }

    }

    @PostMapping("/borrar/{id}")
    public String guardar(@PathVariable("id") Long internoId, @RequestParam("motivo") Long idMotivo,
            RedirectAttributes msgFlash) {
        Interno interno = internoService.buscarPorId(internoId);
        Motivo motivo = motivoService.buscarPorId(idMotivo);

        interno.setActivo(false); // Deshabilitar el interno
        interno.setMotivo(motivo); // Asignar el motivo al interno
        internoService.guardar(interno);

        msgFlash.addFlashAttribute("warning", "Interno Deshabilitado con motivo: " + motivo.getNombre());

        return "redirect:/home";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {

        Interno interno = internoService.buscarPorId(id);

        model.addAttribute("titulo", "Nuevo Interno");
        model.addAttribute("interno", interno);

        return "internos/form";
    }

    

    @ModelAttribute("motivos")
    public List<Motivo> getMotivo() {
        return motivoService.buscarTodos();
    }

   

}
