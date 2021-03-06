package com.bolsadeideas.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Coleccion;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Creador;

public interface IColeccionDao extends JpaRepository<Coleccion, Integer> {
	
	@Query("from Creador")
	public List<Creador> findAllCreadores();

}
