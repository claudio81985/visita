package com.analista.proyecto.Controller;

import com.analista.proyecto.Model.Interno;
import com.analista.proyecto.Model.Modulo;
import com.analista.proyecto.Service.IInternoService;
import com.analista.proyecto.Service.IModuloService;
import com.analista.proyecto.Util.VistaPdf;
import com.lowagie.text.Document;

import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PDFController {
    @Autowired
    private VistaPdf vistaPdf;

    @Autowired
    IInternoService internoService;

    @Autowired
    IModuloService moduloService;

    @GetMapping("/pdf/generar")
    public void generarPdf(@RequestParam("internoId") Long internoId, @RequestParam("moduloId") Long moduloId,
            HttpServletResponse response) {
        Interno interno = internoService.buscarPorId(internoId);
        Modulo modulo = moduloService.buscarPorId(moduloId);

        Map<String, Object> model = new HashMap<>();
        model.put("interno", interno);
        model.put("modulo", modulo);

        try {
            response.setContentType("application/pdf");
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            vistaPdf.buildPdfDocument(model, document, response);
            document.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// @Controller
// public class PDFController {

// @Autowired
// private PresupuestoPdf presupuestoPdf;

// @Autowired
// IClienteService clienteService;

// @Autowired
// ICasaService casaService;

// @Autowired
// IPresupuestoService presupuestoService;

// @GetMapping("/pdf/generar/{id}")
// public ResponseEntity<ByteArrayResource> generarPdf(@PathVariable("id") Long
// clienteId,
// @RequestParam("presupuestoId") Long presupuestoId, HttpServletResponse
// response) {

// Cliente cliente = clienteService.buscarPorId(clienteId);

// // Verificar si el cliente existe
// if (cliente == null) {
// return ResponseEntity.notFound().build();
// }

// Presupuesto presupuesto = presupuestoService.buscarPorId(presupuestoId);

// // Verificar si el presupuesto existe
// if (presupuesto == null) {
// return ResponseEntity.notFound().build();
// }

// Casa casa = cliente.getCasa(); // Obtener la casa directamente del cliente

// Map<String, Object> model = new HashMap<>();
// model.put("cliente", cliente);
// model.put("casa", casa);
// model.put("presupuesto", presupuesto);

// try {
// // Generar el PDF
// ByteArrayOutputStream baos = new ByteArrayOutputStream();
// PdfWriter writer = new PdfWriter(baos);
// PdfDocument pdf = new PdfDocument(writer);
// Document document = new Document(pdf);

// // // // Llamar al método para construir el PDF
// presupuestoPdf.buildPdfDocument(model, document, writer,
// request, response);

// // Cerrar el documento y el escritor
// document.close();
// writer.close();

// // Configurar los encabezados de la respuesta HTTP
// HttpHeaders headers = new HttpHeaders();
// headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;
// filename=presupuesto.pdf");

// // Devolver el PDF como una respuesta HTTP
// return ResponseEntity
// .ok()
// .headers(headers)
// .contentType(MediaType.APPLICATION_PDF)
// .body(new ByteArrayResource(baos.toByteArray()));
// } catch (Exception e) {
// // Manejo de errores
// e.printStackTrace(); // Aquí podrías implementar un registro de errores más
// avanzado

// // Devolver una respuesta de error
// return ResponseEntity
// .status(HttpStatus.INTERNAL_SERVER_ERROR)
// .body(null);
// }
// }

// }
