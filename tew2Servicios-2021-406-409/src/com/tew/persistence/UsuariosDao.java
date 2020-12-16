package com.tew.persistence;

import java.util.List;

import com.tew.model.Amigos;
import com.tew.model.Usuarios;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public interface UsuariosDao {
	
	List<Usuarios> getUsuarios();
	List<Usuarios> getUsuarios(String filtro, String email);
	void save(Usuarios u) throws AlreadyPersistedException;
	void guardarAmigo(Amigos nuevoAmigo) throws AlreadyPersistedException;
	void delete(String email) throws NotPersistedException;
	void reset() throws Exception;
	Usuarios findByEmail(String email);
	


}
