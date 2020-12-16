package impl.tew.business.classes;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.infraestructure.Factories;
import com.tew.model.Publicacion;
import com.tew.persistence.PublicacionDao;
import com.tew.persistence.exception.AlreadyPersistedException;

public class PublicacionAlta {
	
	public void save(Publicacion p) throws EntityAlreadyExistsException{
		
		PublicacionDao publi = Factories.persistence.createPublicacionDao();
		
		try {
			publi.save(p);
		}
		catch(AlreadyPersistedException e) {
			throw new EntityAlreadyExistsException("Ya existe la publicacion " + p);
			
		}
		
		
	}

}
