package impl.tew.business.classes;

import com.tew.infraestructure.Factories;
import com.tew.model.Publicacion;
import com.tew.persistence.PublicacionDao;


public class PublicacionBuscar {

	public Publicacion findById(Long id) throws Exception{

		PublicacionDao publi = Factories.persistence.createPublicacionDao();
		return publi.findById(id);

	}

}
