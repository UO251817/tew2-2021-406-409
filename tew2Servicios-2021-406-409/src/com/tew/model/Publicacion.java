package com.tew.model;

public class Publicacion {
	
	private Long ID;
	private String email;
	private String titulo;
	private String texto;
	private long fecha;
	private String fecha_legible;
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public long getFecha() {
		return fecha;
	}
	public void setFecha(long fecha) {
		this.fecha = fecha;
	}
	public String getFecha_legible() {
		return fecha_legible;
	}
	public void setFecha_legible(String fecha_legible) {
		this.fecha_legible = fecha_legible;
	}
}
