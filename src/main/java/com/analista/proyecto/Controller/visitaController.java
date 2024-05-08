package com.analista.proyecto.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.analista.proyecto.Model.Genero;
import com.analista.proyecto.Model.Interno;
import com.analista.proyecto.Model.Parentesco;
import com.analista.proyecto.Model.Visita;
import com.analista.proyecto.Service.IGeneroService;
import com.analista.proyecto.Service.IInternoService;
import com.analista.proyecto.Service.IParentescoService;
import com.analista.proyecto.Service.ITipoVisitaService;
import com.analista.proyecto.Service.IUploadFileService;
import com.analista.proyecto.Service.IVisitaService;

import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/internos/{id}/visitas")
@SessionAttributes("visita")
public class visitaController {

    @Autowired
    IVisitaService visitaService;

    @Autowired
    IGeneroService generoService;

    @Autowired
    IParentescoService parentescoService;

    @Autowired
    IInternoService internoService;

    @Autowired
    IUploadFileService uploadFileService;

    @Autowired
    ITipoVisitaService tipoVisitaService;

    @GetMapping("/nuevo")
    public String nuevo(@PathVariable("id") Long internoId, Model model) {
        System.out.println("ID del detenido recibido en el controlador nuevo: " + internoId);
        // Aquí puedes cargar los datos necesarios para el formulario de nueva visita
        model.addAttribute("titulo", "CARGAR NUEVA VISITA");
        model.addAttribute("visita", new Visita());
        model.addAttribute("generos", generoService.buscarTodos());
        model.addAttribute("parentescos", parentescoService.buscarTodo());

        // Agrega el ID del interno al modelo para pasarlo al formulario
        model.addAttribute("internoId", internoId);

        return "visitas/formVisita";
    }

    @PostMapping("/guardar")
    public String guardarVisita(@RequestParam("internoId") Long internoId,
            @Valid Visita visita,
            RedirectAttributes msgFlash,
            SessionStatus status,
            BindingResult result,
            Model model,
            @RequestParam("genero") Long idGenero,
            @Nonnull @RequestParam("file") MultipartFile imagen,
            @RequestParam("parentesco") Long idParentesco) {
        if (imagen.isEmpty()) {
            model.addAttribute("danger", "La carga de la foto es obligatoria.");
            return "visitas/formVisita";
        }
        if (result.hasErrors()) {
            model.addAttribute("danger", "Corrija los errores...");
            return "visitas/formVisita";
        }
        try {
            Interno interno = internoService.buscarPorId(internoId);

            // Crear la carpeta de documentos si no existe
            String carpetaDocumentosPath = Paths.get("documentos").toAbsolutePath().toString();
            File carpetaDocumentos = new File(carpetaDocumentosPath);
            if (!carpetaDocumentos.exists()) {
                carpetaDocumentos.mkdir();
            }

            // Crear la subcarpeta para la visita dentro de la carpeta del interno
            String carpetaInterno = carpetaDocumentosPath + "/" + interno.getNombreApellido() + "-" + interno.getId();
            File carpetaInternoFile = new File(carpetaInterno);
            if (!carpetaInternoFile.exists()) {
                carpetaInternoFile.mkdir();
            }

            // Crear la carpeta para la visita
            String carpetaVisita = carpetaInterno + "/" + visita.getNombre();
            File carpetaVisitaFile = new File(carpetaVisita);
            if (!carpetaVisitaFile.exists()) {
                carpetaVisitaFile.mkdir();
            }

            // Guardar la imagen en la carpeta de la visita
            String nombreImagen = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();
            Path rutaImagen = Paths.get(carpetaVisita, nombreImagen);
            Files.write(rutaImagen, imagen.getBytes());

            // Crear una nueva instancia de Visita
            Visita nuevaVisita = new Visita();
            nuevaVisita.setNombre(visita.getNombre());
            nuevaVisita.setApellido(visita.getApellido());
            nuevaVisita.setFechaNacimiento(visita.getFechaNacimiento());
            nuevaVisita.setDomicilio(visita.getDomicilio());
            nuevaVisita.setEdad(visita.getEdad());
            nuevaVisita.setGenero(visita.getGenero());
            nuevaVisita.setParentesco(visita.getParentesco());
            nuevaVisita.setDni(visita.getDni());
            nuevaVisita.setImagen(nombreImagen);

            // Asignar el interno a la nueva visita
            nuevaVisita.setInterno(interno);

            // Asignar el nombre de la imagen a la visita y guardarla nuevamente

            visita.setGenero(generoService.buscarPorId(idGenero));
            visita.setParentesco(parentescoService.buscarPorId(idParentesco));
            visitaService.guardar(nuevaVisita);
            interno.addVisita(nuevaVisita);

            msgFlash.addFlashAttribute("success", "Guardado Correctamente.");
            status.setComplete();
            return "redirect:/home";
        } catch (IOException ex) {
            ex.printStackTrace();
            model.addAttribute("danger", "Error al guardar la visita.");
            model.addAttribute("danger", "Ya existe una visita cargada con el DNI N: " + visita.getDni());
            return "visitas/formVisita";
        } 
    }

    @ModelAttribute("generos")
    public List<Genero> getGeneros() {
        return generoService.buscarTodos();
    }

    @ModelAttribute("parentescos")
    public List<Parentesco> getParentescos() {
        return parentescoService.buscarTodo();
    }

}
