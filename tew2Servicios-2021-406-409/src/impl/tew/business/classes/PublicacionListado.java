package impl.tew.business.classes;

import java.util.List;

import com.tew.infraestructure.Factories;
import com.tew.model.Publicacion;
import com.tew.persistence.PublicacionDao;

public class PublicacionListado {
	
		
		public List<Publicacion> getPublicaciones() throws Exception {
			
			PublicacionDao publi = Factories.persistence.createPublicacionDao();
			return publi.getPublicaciones();

		}

		public List<Publicacion> getPublicaciones(String email) {
			
			PublicacionDao publi = Factories.persistence.createPublicacionDao();
			return publi.getPublicaciones(email);
			
		}

		public List<Publicacion> getPublicacionesAmigos(String email) {
			
			PublicacionDao publi = Factories.persistence.createPublicacionDao();
			return publi.getPublicacionesAmigos(email);
		}
}


