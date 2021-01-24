package com.bolsadeideas.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Coleccion;

public interface IColeccionDao extends CrudRepository<Coleccion, Integer> {

}
