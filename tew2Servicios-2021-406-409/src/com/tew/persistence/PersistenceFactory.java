package com.tew.persistence;

public interface PersistenceFactory {
	
	UsuariosDao createUsuariosDao();
	PublicacionDao createPublicacionDao();
	AmigosDao createAmigosDao();

}
