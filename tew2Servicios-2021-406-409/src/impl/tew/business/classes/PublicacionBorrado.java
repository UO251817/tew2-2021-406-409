package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infraestructure.Factories;
import com.tew.persistence.PublicacionDao;


public class PublicacionBorrado {
	
	public void delete(String email) throws  EntityNotFoundException{
		
		PublicacionDao publi = Factories.persistence.createPublicacionDao();
		
		try {
			publi.delete(email);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
