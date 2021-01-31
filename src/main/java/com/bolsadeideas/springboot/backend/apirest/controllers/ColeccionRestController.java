package com.bolsadeideas.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Coleccion;
import com.bolsadeideas.springboot.backend.apirest.models.service.IColeccionService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/apicolec")
public class ColeccionRestController {

	@Autowired
	private IColeccionService coleccionService;

	@GetMapping("/colecciones")
	public List<Coleccion> getColecciones() {

		return coleccionService.findAll();
	}

	// Spring tiene la clase ResponseEntity que permite enviar en la respuesta el
	// mensaje de error y
	// el objeto
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

	@DeleteMapping("/colecciones/{idColeccion}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Integer idColeccion) {
		Map<String, Object> response = new HashMap<>();

		try {
			coleccionService.delete(idColeccion);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al intentar eliminar la colección.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La coleccion se ha eliminado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
	}

}
