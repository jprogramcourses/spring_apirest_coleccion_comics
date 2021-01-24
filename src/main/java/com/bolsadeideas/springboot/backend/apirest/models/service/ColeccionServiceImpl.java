package com.bolsadeideas.springboot.backend.apirest.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.backend.apirest.models.dao.IColeccionDao;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Coleccion;

@Service
public class ColeccionServiceImpl implements IColeccionService {
	
	@Autowired
	private IColeccionDao coleccionDao;

	@Override
	@Transactional(readOnly = true)
	public List<Coleccion> findAll() {
		
		return (List<Coleccion>) coleccionDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Coleccion findById(Integer idColeccion) {
		
		return coleccionDao.findById(idColeccion).orElse(null);
	}

	@Override
	public Coleccion save(Coleccion coleccion) {
		
		return coleccionDao.save(coleccion);
	}

	@Override
	public void delete(Integer idColeccion) {
		
		coleccionDao.deleteById(idColeccion);		
	}

	@Override
	public Coleccion update(Coleccion coleccion) {
		
		Coleccion coleccionToUpdate = coleccionDao.findById(coleccion.getIdColeccion()).orElse(null);
		
		return coleccionDao.save(coleccion);
	}

}
