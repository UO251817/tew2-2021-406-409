package com.tew.business;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Publicacion;

public interface PublicacionService {
	
	List<Publicacion> getPublicaciones() throws Exception;
	List<Publicacion> getPublicaciones(String email) throws Exception;
	List<Publicacion> getPublicacionesAmigos(String email);
	void save(Publicacion p) throws EntityAlreadyExistsException;
	void update(Publicacion p ) throws EntityNotFoundException;
	void delete(String email) throws EntityNotFoundException, Exception;
	Publicacion findById(Long id) throws Exception;
}