package com.bolsadeideas.springboot.backend.apirest.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Coleccion;

public interface IColeccionService {
	
	public List<Coleccion> findAll();
	
	public Page<Coleccion> findAll(Pageable pageable);
	
	public Coleccion findById(Integer idColeccion);
	
	public Coleccion save(Coleccion coleccion);
	
	public void delete(Integer idColeccion);
	
	public Coleccion update(Coleccion coleccion);

}
