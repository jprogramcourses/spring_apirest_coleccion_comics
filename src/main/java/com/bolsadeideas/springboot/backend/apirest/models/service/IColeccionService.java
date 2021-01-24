package com.bolsadeideas.springboot.backend.apirest.models.service;

import java.util.List;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Coleccion;

public interface IColeccionService {
	
	public List<Coleccion> findAll();
	
	public Coleccion findById(Integer idColeccion);
	
	public Coleccion save(Coleccion coleccion);
	
	public void delete(Integer idColeccion);
	
	public Coleccion update(Coleccion coleccion);

}
