package com.tew.business;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Amigos;

public interface AmigosService {
	
	List<Amigos> getAmigos() throws Exception;
	List<Amigos> getAmigos(String email_usuario) throws Exception;
	List<Amigos> getCandidatos(String email_usuario) throws Exception;
	void save(Amigos a) throws EntityAlreadyExistsException;
	void update(Amigos a) throws EntityNotFoundException;
	void delete(String email_amigo) throws EntityNotFoundException;
	void delete(String email_usuario, String email_amigo) throws EntityNotFoundException;
	Amigos findByEmail(String email_usuario) throws Exception;
	void aceptar(String email_usuario, String email_amigo) throws EntityNotFoundException;

}
