package com.tew.model;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Amigos")
public class Amigos {
	
	private String email_usuario;
	private String email_amigo;
	private boolean aceptada;
	
	
	public Amigos() {
		
	}
	
	
	public Amigos(String email_usuario, String email_amigo, boolean aceptada) {

		this.email_usuario = email_usuario;
		this.email_amigo = email_amigo;
		this.aceptada = aceptada;
	}


	@XmlElement
	public String getEmail_usuario() {
		return email_usuario;
	}
	public void setEmail_usuario(String email_usuario) {
		this.email_usuario = email_usuario;
	}
	@XmlElement
	public String getEmail_amigo() {
		return email_amigo;
	}
	public void setEmail_amigo(String email_amigo) {
		this.email_amigo = email_amigo;
	}
	@XmlElement
	public boolean isAceptada() {
		return aceptada;
	}
	public void setAceptada(boolean aceptada) {
		this.aceptada = aceptada;
	}
	
	
}
