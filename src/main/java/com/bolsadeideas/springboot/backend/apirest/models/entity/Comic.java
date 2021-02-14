package com.bolsadeideas.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@Entity
//@Table(name = "comics")
public class Comic implements Serializable {

//	private static final long serialVersionUID = 1L;
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	private String titulo;
//	@Column(nullable=false)
//	private int numero;
//	private String descripcion;
//	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="id_coleccion")
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	private Coleccion coleccion;
//	
//	public Comic() {
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getTitulo() {
//		return titulo;
//	}
//
//	public void setTitulo(String titulo) {
//		this.titulo = titulo;
//	}
//
//	public int getNumero() {
//		return numero;
//	}
//
//	public void setNumero(int numero) {
//		this.numero = numero;
//	}
//
//	public String getDescripcion() {
//		return descripcion;
//	}
//
//	public void setDescripcion(String descripcion) {
//		this.descripcion = descripcion;
//	}
//
//	public Coleccion getColeccion() {
//		return coleccion;
//	}
//
//	public void setColeccion(Coleccion coleccion) {
//		this.coleccion = coleccion;
//	}

}
