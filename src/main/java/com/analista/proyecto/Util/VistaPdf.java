package com.analista.proyecto.Util;

import com.analista.proyecto.Model.Interno;
import com.analista.proyecto.Model.Modulo;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;

import com.lowagie.text.pdf.PdfPTable;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class VistaPdf {

    public void buildPdfDocument(Map<String, Object> model, Document document, HttpServletResponse response) {
        Modulo modulo = (Modulo) model.get("modulo");
        List<Interno> internos = (List<Interno>) model.get("internos");

        try {
            // Crear el encabezado del documento con el nombre del módulo
            Paragraph header = new Paragraph("Módulo: " + modulo.getNombre());
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);

            // Agregar información de todos los internos relacionados con el módulo
            PdfPTable tablaInterno = new PdfPTable(2);
            tablaInterno.setWidthPercentage(100);
            tablaInterno.addCell("Nombre del Interno");
            tablaInterno.addCell("DNI");

            for (Interno interno : internos) {
                tablaInterno.addCell(interno.getNombreApellido());
                tablaInterno.addCell(interno.getDni());
            }

            document.add(tablaInterno);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
