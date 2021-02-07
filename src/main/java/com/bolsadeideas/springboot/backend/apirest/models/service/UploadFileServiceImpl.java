package com.bolsadeideas.springboot.backend.apirest.models.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

	private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);

	private static final String PATH_UPLOAD = "uploads";

	@Override
	public Resource cargar(String nombreFoto) throws MalformedURLException {

		Path rutaArchivo = getPath(nombreFoto);
		log.info(rutaArchivo.toString());
		Resource recurso = new UrlResource(rutaArchivo.toUri());

		if (!recurso.exists() && !recurso.isReadable()) {
			rutaArchivo = Paths.get("src/main/resources/static/images").resolve("not-user.svg").toAbsolutePath();

			recurso = new UrlResource(rutaArchivo.toUri());

			log.error("Error al cargar la imagen");
		}

		return recurso;
	}

	@Override
	public String copiar(MultipartFile archivo) throws IOException {

		String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
		Path pathArchivo = getPath(nombreArchivo);

		log.info(pathArchivo.toString());

		Files.copy(archivo.getInputStream(), pathArchivo);

		return nombreArchivo;
	}

	@Override
	public boolean eliminar(String nombreFoto) {

		if(nombreFoto != null && nombreFoto.isEmpty()) {
			Path rutaImagenAnterior = getPath(nombreFoto);
			File archivoImagenAnterior = rutaImagenAnterior.toFile();
			if(archivoImagenAnterior.exists() && archivoImagenAnterior.canRead()) {
				archivoImagenAnterior.delete();
				return true;
			}
		}
		
		return false;
	}

	@Override
	public Path getPath(String nombreFoto) {

		return Paths.get(PATH_UPLOAD).resolve(nombreFoto).toAbsolutePath();
	}

}
