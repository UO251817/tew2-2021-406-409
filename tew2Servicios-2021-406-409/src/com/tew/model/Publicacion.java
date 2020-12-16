package com.tew.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Publicacion")
public class Publicacion {
	
	private Long ID;
	private String email;
	private String titulo;
	private String texto;
	private long fecha;
	private String fecha_legible;
	
	
	public Publicacion() {
		
	}
	
	public Publicacion(Long iD, String email, String titulo, String texto, long fecha, String fecha_legible) {
		ID = iD;
		this.email = email;
		this.titulo = titulo;
		this.texto = texto;
		this.fecha = fecha;
		this.fecha_legible = fecha_legible;
	}
	
	
	@XmlElement
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	@XmlElement
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@XmlElement
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	@XmlElement
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	@XmlElement
	public long getFecha() {
		return fecha;
	}
	public void setFecha(long fecha) {
		this.fecha = fecha;
	}
	@XmlElement
	public String getFecha_legible() {
		return fecha_legible;
	}
	public void setFecha_legible(String fecha_legible) {
		this.fecha_legible = fecha_legible;
	}
}
