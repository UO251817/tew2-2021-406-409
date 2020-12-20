package impl.tew.business;

import java.util.List;

import com.tew.business.AmigosService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Amigos;

import impl.tew.business.classes.AmigosOperaciones;

public class SimpleAmigosService implements AmigosService{

	@Override
	public List<Amigos> getAmigos() throws Exception {
		
		return new AmigosOperaciones().getAmigos();
	}
	
	@Override
	public List<Amigos> getAmigos(String email_usuario) throws Exception {
		
		return new AmigosOperaciones().getAmigos(email_usuario);
	}
	
	
	@Override
	public List<Amigos> getCandidatos(String email_usuario) throws Exception {
	
		return new AmigosOperaciones().getCandidatos(email_usuario);
	}
	
	@Override
	public void save(Amigos a) throws EntityAlreadyExistsException {
		
		new AmigosOperaciones().save(a);
	}

	@Override
	public void update(Amigos a) throws EntityNotFoundException {
		
		
	}

	@Override
	public void delete(String email_usuario) throws EntityNotFoundException {
		new AmigosOperaciones().delete(email_usuario);
		
		
	}

	@Override
	public void delete(String email_usuario, String email_amigo) throws EntityNotFoundException {
		
		
	}

	@Override
	public Amigos findByEmail(String email_usuario) throws Exception {
		
		return new AmigosOperaciones().findByEmail(email_usuario);
	}

	@Override
	public void aceptar(String email_usuario, String email_amigo) throws EntityNotFoundException {
		new AmigosOperaciones().aceptar(email_usuario,email_amigo);
		
	}
}
