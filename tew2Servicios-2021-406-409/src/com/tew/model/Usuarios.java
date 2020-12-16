package com.tew.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Usuarios")
public class Usuarios {
	
	private String email;
	private String passwd;
	private String passwd2;
	private String rol;
	private String nombre;
	
	public Usuarios() {
		
		
	}
	
	public Usuarios(String email, String passwd, String passwd2, String rol, String nombre) {
		
		this.email = email;
		this.passwd = passwd;
		this.passwd2 = passwd2;
		this.rol = rol;
		this.nombre = nombre;
	}

	@XmlElement
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@XmlElement
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	@XmlElement
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	@XmlElement
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@XmlElement
	public String getPasswd2() {
		return passwd2;
	}
	public void setPasswd2(String passwd2) {
		this.passwd2 = passwd2;
	}
	@Override
	public String toString() {
		return "Usuarios [email=" + email + ", passwd=" + passwd + ", passwd2=" + passwd2 + ", rol=" + rol + ", nombre="
				+ nombre + "]";
	}
}
