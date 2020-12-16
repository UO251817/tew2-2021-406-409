package com.tew.business;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Amigos;
import com.tew.model.Usuarios;

public interface UsuariosService {
	
	
	List<Usuarios> getUsuarios() throws Exception;
	List<Usuarios> getUsuarios(String filtro, String email) throws EntityNotFoundException;
	void save(Usuarios u) throws EntityAlreadyExistsException;
	void guardarAmigo(Amigos nuevoAmigo)throws EntityAlreadyExistsException;
	void delete(String email) throws EntityNotFoundException;
	void reinicioBaseDatos() throws Exception;
	Usuarios usuariosFindByEmail (String email) throws EntityNotFoundException;
	
	

}