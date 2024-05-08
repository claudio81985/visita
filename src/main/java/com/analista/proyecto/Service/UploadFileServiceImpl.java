package com.analista.proyecto.Service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


import org.slf4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;



@Service
public class UploadFileServiceImpl implements IUploadFileService {

    private final Logger log = org.slf4j.LoggerFactory.getLogger(getClass());

    public final static String UPLOADS_FOLDER = "documentos";

    @Override
    public Resource load(String filename) throws MalformedURLException {
        Path pathFoto = getPath(filename);
        log.info("Path foto: " + pathFoto);
        Resource recurso = null;

        recurso = new UrlResource(pathFoto.toUri());
        if (!recurso.exists() || !recurso.isReadable()) {
            throw new RuntimeException("Error: no se pudo encontrar la imagen " + pathFoto.toString());
        }

        return recurso;
    }

    @Override
    public String copy(MultipartFile file) throws IOException {
        String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path rootPath = getPath(uniqueFilename);

        log.info("rootPath: " + rootPath); // Path relativo al proyecto

      
        Files.copy(file.getInputStream(), rootPath);

        return uniqueFilename;
    }

    @Override
    public boolean delete(String filename) {

        Path rootPath = getPath(filename);
        File archivo = rootPath.toFile();

        if (archivo.exists() && archivo.canRead()) {
            if (archivo.delete()) {
                return true;
            }
        }
        return false;
    }

    public Path getPath(String filename) {
        return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_FOLDER).toFile());
    }

    @Override
    public void init() throws IOException {
        Files.createDirectory(Paths.get(UPLOADS_FOLDER));
    }

    @Override
public String guardarArchivo(MultipartFile archivo, String nombreVisita, Long idVisita) throws IOException {
    // Generar un nombre único para el archivo
    String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename();

    // Obtener la ruta completa de la carpeta de la visita
    String carpetaVisita = UPLOADS_FOLDER + File.separator + nombreVisita + "-" + idVisita;
    Path rutaCompleta = Paths.get(carpetaVisita).resolve(nombreArchivo).toAbsolutePath();

    // Crear la estructura de carpetas si no existe
    Files.createDirectories(rutaCompleta.getParent());

    // Guardar los bytes del archivo en la ruta completa
    Files.write(rutaCompleta, archivo.getBytes());

    return nombreArchivo;
}


    private final Path directorioBase = Paths.get("/documentos");

    public Resource cargarArchivo(String nombreArchivo) {
        try {
            Path archivo = directorioBase.resolve(nombreArchivo);
            Resource resource = new UrlResource(archivo.toUri());

            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                return null;
            }
        } catch (MalformedURLException e) {
            
            return null;
        }
    }

}