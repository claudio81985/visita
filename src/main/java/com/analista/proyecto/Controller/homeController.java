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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.analista.proyecto.Model.Elemento;
import com.analista.proyecto.Model.Interno;
import com.analista.proyecto.Model.Modulo;
import com.analista.proyecto.Service.IElementoService;
import com.analista.proyecto.Service.IInternoService;
import com.analista.proyecto.Service.IModuloService;
import com.analista.proyecto.Service.IMotivoService;

import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;

@Controller
@SessionAttributes("elemento")
public class homeController {

    @Autowired
    IInternoService internoService;

    @Autowired
    IElementoService elementoService;

    @Autowired
    IMotivoService motivoService;

    @Autowired
    IModuloService moduloService;

    @GetMapping({ "/", "/home" })
    public String home(Model model) {

        model.addAttribute("titulo", "REGISTRO DE VISITAS");
        model.addAttribute("internos", internoService.buscarTodo());
        model.addAttribute("elementos", elementoService.buscarTodos());
        model.addAttribute("elemento", new Elemento());
        model.addAttribute("motivos", motivoService.buscarTodos());
        model.addAttribute("modulos", moduloService.buscarTodos());

        return "home";

    }

    @PostMapping("/guardar-elemento/{id}")
    public String guardar(@PathVariable("id") Long internoId, @Valid Elemento elemento,
            RedirectAttributes msgFlash, SessionStatus status, BindingResult result, Model model,
            @Nonnull @RequestParam("file_2") MultipartFile factura) {

        if (factura.isEmpty()) {
            model.addAttribute("yourInternoId", internoId);
            model.addAttribute("errorArchivos", "La Nota de Rechazo es obligatoria.");
            return "redirect:/home";
        }

        if (result.hasErrors()) {
            model.addAttribute("titulo", "CARGAR NOTA");
            model.addAttribute("yourInternoId", internoId);
            return "redirect:/home";
        }

        try {
            System.out.println("ID del detenido recibido en el controlador nuevo: " + internoId);

            // Obtener el interno asociado a través del ID
            Interno interno = internoService.buscarPorId(internoId);
            // Establecer la relación entre la visita y el interno

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

            // Crear la carpeta para interno
            String carpetaElemento = carpetaInterno + "/" + elemento.getNombre();
            File carpetaElementoFile = new File(carpetaElemento);
            if (!carpetaElementoFile.exists()) {
                carpetaElementoFile.mkdir();
            }

            // Guardar la imagen en la carpeta del interno
            // Obtener un nombre único para la factura
            String nombreFactura = UUID.randomUUID().toString() + "_" + factura.getOriginalFilename();
            Path rutaImagen = Paths.get(carpetaElemento, nombreFactura);
            Files.write(rutaImagen, factura.getBytes());

            // Crear una nueva instancia de Visita
            Elemento nuevoElemento = new Elemento();
            nuevoElemento.setNombre(elemento.getNombre());
            nuevoElemento.setDescripcion(elemento.getDescripcion());
            nuevoElemento.setFechaAlta(elemento.getFechaAlta());
            nuevoElemento.setTipo(elemento.getTipo());
            nuevoElemento.setFactura(nombreFactura);

            nuevoElemento.setInterno(interno);

            elementoService.guardar(nuevoElemento);
            interno.addElemento(nuevoElemento);

            msgFlash.addFlashAttribute("success", "Elemento Guardado Correctamente.");
            status.setComplete();

            return "redirect:/home";

        } catch (IOException ex) {
            ex.printStackTrace();
            model.addAttribute("danger", "Error al guardar el Elemento.");
            return "redirect:/home";
        }
    }

    @GetMapping("/internosPorModulo/{idModulo}")
    public String listarInternosPorModulo(@PathVariable("idModulo") Long idModulo, Model model) {
        Modulo modulo = moduloService.buscarPorId(idModulo);
        List<Interno> internos = internoService.buscarPorModulo(modulo);
        String nombreModulo = modulo.getNombre(); // Obtener el nombre del módulo

        model.addAttribute("internos", internos);
        model.addAttribute("nombreModulo", nombreModulo);
        model.addAttribute("idModulo", idModulo); // Agregar el ID del módulo al modelo

        return "ListadoPorModulo";
    }

    @ModelAttribute("modulos")
    public List<Modulo> getModulos() {
        return moduloService.buscarTodos();
    }

}