package impl.tew.business.classes;

import java.util.List;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.infraestructure.Factories;
import com.tew.model.Amigos;
import com.tew.persistence.AmigosDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public class AmigosOperaciones {
	
	public void save(Amigos a) throws EntityAlreadyExistsException{
		AmigosDao amigos = Factories.persistence.createAmigosDao();

		try {
			amigos.save(a);
		}
		catch(AlreadyPersistedException e){
			throw new EntityAlreadyExistsException("No se ha podido guardar la solicitud al amigo " + a + e);
		}
	}
	
	public Amigos findByEmail(String email_usuario) throws Exception{

		AmigosDao amigos = Factories.persistence.createAmigosDao();
		return amigos.findByEmail(email_usuario);

	}

	public List<Amigos> getAmigos() throws Exception{

		AmigosDao amigos = Factories.persistence.createAmigosDao();
		return amigos.getAmigos();
	}

	public List<Amigos> getAmigos(String email_usuario) {
		
		AmigosDao amigos = Factories.persistence.createAmigosDao();
		return amigos.getAmigos(email_usuario);
	}
	
	
	public List<Amigos> getCandiatos(String email_usuario) {

		AmigosDao amigos = Factories.persistence.createAmigosDao();
		return amigos.getCandidatos(email_usuario);
	}

	
	
	public void delete(String email_usuario) throws EntityNotFoundException{

		AmigosDao amigos = Factories.persistence.createAmigosDao();

		try {
			amigos.delete(email_usuario);
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void aceptar(String email_usuario, String email_amigo) throws EntityNotFoundException{
		AmigosDao amigos = Factories.persistence.createAmigosDao();
		try {
			amigos.aceptar(email_usuario,email_amigo);
			
		} catch (NotPersistedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
