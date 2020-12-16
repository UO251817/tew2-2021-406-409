package com.tew.business;

public interface ServicesFactory {
	
	UsuariosService createUsuariosService();
	PublicacionService createPublicacionService();
	AmigosService createAmigosService();
	LoginService createLoginService();
}
