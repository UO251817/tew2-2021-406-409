package com.tew.persistence;

import java.util.List;


import com.tew.model.Amigos;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public interface AmigosDao {
	
	List<Amigos> getAmigos();
	List<Amigos> getAmigos(String email_usuario);
	List<Amigos> getCandidatos(String email_usuario);
	void save(Amigos a) throws AlreadyPersistedException;
	void delete(String email_amigo) throws Exception;
	Amigos findByEmail(String email_usuario);
	void aceptar(String email_usuario, String email_amigo) throws NotPersistedException;


}
