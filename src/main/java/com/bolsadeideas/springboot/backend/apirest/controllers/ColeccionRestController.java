package com.bolsadeideas.springboot.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/apicolec")
public class ColeccionRestController {
	
	@Autowired
	private IColeccionService coleccionService;
	
	@GetMapping("/colecciones")
	public List<Coleccion> getColecciones() {
		
		return coleccionService.findAll();
	}
	
	@GetMapping("/colecciones/{idColeccion}")
	public Coleccion getColeccion(@PathVariable Integer idColeccion) {
		return coleccionService.findById(idColeccion);
	}
	
	@PostMapping("/colecciones")
	@ResponseStatus(HttpStatus.CREATED)
	public Coleccion create(@RequestBody Coleccion coleccion) {
		return coleccionService.save(coleccion);
	}
	
	@PutMapping("/colecciones/{idColeccion}")
	@ResponseStatus(HttpStatus.CREATED)
	public Coleccion update(@RequestBody Coleccion coleccion, @PathVariable Integer idColeccion) {
		Coleccion coleccionToUpdate = coleccionService.findById(idColeccion);
		if(coleccionToUpdate != null) {
			coleccionToUpdate.setNombre(coleccion.getNombre());
			coleccionToUpdate.setNumerosTotales(coleccion.getNumerosTotales());
			coleccionToUpdate.setNumerosDisponibles(coleccion.getNumerosDisponibles());
			coleccionToUpdate.setCreateAt(coleccion.getCreateAt());
		}
		return coleccionService.save(coleccionToUpdate);
	}
	
	@DeleteMapping("/colecciones/{idColeccion}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer idColeccion) {
		coleccionService.delete(idColeccion);
	}

}
