package impl.tew.business.resteasy;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.business.resteasy.AmigosServicesRs;
import com.tew.model.Amigos;
import impl.tew.business.classes.AmigosOperaciones;


public class AmigosServicesRsImpl implements AmigosServicesRs {

	@Override
	public List<Amigos> getAmigos() {
		
		try {
			return new AmigosOperaciones().getAmigos();
		} catch (Exception e) {
		
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Amigos> getAmigos(String email) throws Exception {

		try {
			return new AmigosOperaciones().getAmigos(email);
		} catch (Exception e) {
		
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Amigos> getCandidatos(String email) throws Exception {
	
		return new AmigosOperaciones().getCandidatos(email);
	}

	@Override
	public Amigos findByEmail(String email_usuario) throws EntityNotFoundException {

		try {
			return new AmigosOperaciones().findByEmail(email_usuario);
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void update(Amigos a) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Amigos a) throws EntityAlreadyExistsException {
		
		new AmigosOperaciones().save(a);
		
	}

	@Override
	public void delete(String email_amigo) throws EntityNotFoundException {
		new AmigosOperaciones().delete(email_amigo);
		
	}


	@Override
	public void aceptar(String email_usuario, String email_amigo) throws EntityNotFoundException {
		
		new AmigosOperaciones().aceptar(email_usuario, email_amigo);
		
	}

	@Override
	public void delete(String email_usuario, String email_amigo) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		
	}

}
