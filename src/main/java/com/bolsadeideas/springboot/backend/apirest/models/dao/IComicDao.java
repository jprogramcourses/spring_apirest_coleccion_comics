package com.bolsadeideas.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Coleccion;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Comic;

public interface IComicDao extends JpaRepository<Comic, Long> {
	
	@Query("from Coleccion")
	public List<Coleccion> findAllColecciones();

}
