package com.bolsadeideas.springboot.backend.apirest.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "colecciones")
public class Coleccion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idColeccion;
	private String nombre;
	
	@Column(name = "num_totales")
	private int numerosTotales;
	@Column(name = "num_disponibles")
	private int numerosDisponibles;
	
	@Column(name = "create_at")
	private Date createAt;
	
	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}
	
	// Transformar a listado de objeto Comic
//	@Column(name = "list_num_disp")
//	private List<Integer> listadoNumerosDisponible;
	
	public Coleccion() {
		super();
	}
	
	public int getIdColeccion() {
		return idColeccion;
	}
	
	public void setIdColeccion(int idColeccion) {
		this.idColeccion = idColeccion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getNumerosTotales() {
		return numerosTotales;
	}
	
	public void setNumerosTotales(int numerosTotales) {
		this.numerosTotales = numerosTotales;
	}
	
	public int getNumerosDisponibles() {
		return numerosDisponibles;
	}
	
	public void setNumerosDisponibles(int numerosDisponibles) {
		this.numerosDisponibles = numerosDisponibles;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
//	public List<Integer> getListadoNumerosDisponible() {
//		return listadoNumerosDisponible;
//	}
//	
//	public void setListadoNumerosDisponible(List<Integer> listadoNumerosDisponible) {
//		this.listadoNumerosDisponible = listadoNumerosDisponible;
//	}

}
