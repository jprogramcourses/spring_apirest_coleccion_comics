package com.bolsadeideas.springboot.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Coleccion;

public interface IColeccionDao extends JpaRepository<Coleccion, Integer> {

}
