package com.tew.persistence;

import java.util.List;


import com.tew.model.Publicacion;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public interface PublicacionDao {
	
	List<Publicacion> getPublicaciones();
	List<Publicacion> getPublicaciones(String email_usuario);
	List<Publicacion> getPublicacionesAmigos(String email);
	void save(Publicacion p) throws AlreadyPersistedException;
	Publicacion findById(Long id);
	void delete(String email) throws NotPersistedException;
	
}
