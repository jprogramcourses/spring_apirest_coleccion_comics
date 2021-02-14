package com.bolsadeideas.springboot.backend.apirest.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Coleccion;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Creador;
import com.bolsadeideas.springboot.backend.apirest.models.service.IColeccionService;
import com.bolsadeideas.springboot.backend.apirest.models.service.IUploadFileService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/apicolec")
public class ColeccionRestController {
	
	private final Logger log = LoggerFactory.getLogger(ColeccionRestController.class);
	
	private static final String PATH = "uploads";

	@Autowired
	private IColeccionService coleccionService;
	
	@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping("/colecciones")
	public List<Coleccion> getColecciones() {

		return coleccionService.findAll();
	}
	
	@GetMapping("/colecciones/page/{page}")
	public Page<Coleccion> getColecciones(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 5);
		return coleccionService.findAll(pageable);
	}

	// Spring tiene la clase ResponseEntity que permite enviar en la respuesta el
	// mensaje de error y
	// el objeto
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/colecciones/{idColeccion}")
	public ResponseEntity<?> getColeccion(@PathVariable Integer idColeccion) {
		Coleccion coleccion = null;
		Map<String, Object> response = new HashMap<>();

		try {
			coleccion = coleccionService.findById(idColeccion);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (coleccion == null) {
			response.put("mensaje", "La colección no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Coleccion>(coleccion, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/colecciones")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Coleccion coleccion, BindingResult result) {
		Coleccion coleccionNueva = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
//			List<String> errors = new ArrayList<>();
//			for(FieldError err : result.getFieldErrors()) {
//				errors.add("Error en el campo " + err.getField() + ": " + err.getDefaultMessage());
//			}

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "Error en el campo " + err.getField() + ": " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			coleccionNueva = coleccionService.save(coleccion);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la creación en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La colección se ha creado con éxito");
		response.put("coleccion", coleccionNueva);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/colecciones/{idColeccion}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid @RequestBody Coleccion coleccion, BindingResult result, @PathVariable Integer idColeccion) {
		Coleccion coleccionToUpdate = coleccionService.findById(idColeccion);
		Coleccion coleccionUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "Error en el campo " + err.getField() + ": " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			coleccionToUpdate.setNombre(coleccion.getNombre());
			coleccionToUpdate.setNumerosTotales(coleccion.getNumerosTotales());
			coleccionToUpdate.setNumerosDisponibles(coleccion.getNumerosDisponibles());
			coleccionToUpdate.setCreador(coleccion.getCreador());
			coleccionUpdated = coleccionService.save(coleccionToUpdate);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar al intentar actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (coleccion == null) {
			response.put("mensaje",
					"La colección ".concat(coleccion.getNombre()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		response.put("mensaje", "La coleccion se ha actualizado con éxito");
		response.put("coleccion", coleccionUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("/colecciones/{idColeccion}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Integer idColeccion) {
		Map<String, Object> response = new HashMap<>();

		try {
			Coleccion coleccion = coleccionService.findById(idColeccion);
			
			String imagenAnterior = coleccion.getImagen();
			uploadFileService.eliminar(imagenAnterior);
			
			coleccionService.delete(idColeccion);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al intentar eliminar la colección.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La coleccion se ha eliminado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/colecciones/upload")
	public ResponseEntity<?> uploadImage(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Integer id){
		Map<String, Object> response = new HashMap<>();
		
		Coleccion coleccion = coleccionService.findById(id);
		
		if(!archivo.isEmpty()) {
			
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadFileService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al intentar subir el archivo");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String imagenAnterior = coleccion.getImagen();
			uploadFileService.eliminar(imagenAnterior);
			
			coleccion.setImagen(nombreArchivo);
			
			coleccionService.save(coleccion);
			
			response.put("coleccion", coleccion);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/img/{nombreImagen:.+}")
	public ResponseEntity<Resource> verImagen(@PathVariable String nombreImagen){
		
		Resource recurso = null;
		
		try {
			recurso = uploadFileService.cargar(nombreImagen);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/colecciones/creadores")
	public List<Creador> listarCreadores(){
		return coleccionService.findAllCreadores();
	}

}
