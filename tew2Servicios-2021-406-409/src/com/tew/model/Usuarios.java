package com.tew.model;

public class Usuarios {
	
	private String email;
	private String passwd;
	private String passwd2;
	private String rol;
	private String nombre;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
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
