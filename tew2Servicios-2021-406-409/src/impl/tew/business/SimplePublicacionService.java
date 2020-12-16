package impl.tew.business;

import java.util.List;

import com.tew.business.PublicacionService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Publicacion;

import impl.tew.business.classes.PublicacionAlta;
import impl.tew.business.classes.PublicacionBorrado;
import impl.tew.business.classes.PublicacionBuscar;
import impl.tew.business.classes.PublicacionListado;

public class SimplePublicacionService implements PublicacionService{

	@Override
	public List<Publicacion> getPublicaciones() throws Exception {
		
		return new PublicacionListado().getPublicaciones();
		
	}
	
	@Override
	public List<Publicacion> getPublicaciones(String email) throws Exception {
		
		return new PublicacionListado().getPublicaciones(email);
		
	}
	
	@Override
	public List<Publicacion> getPublicacionesAmigos(String email) {
		
		return new PublicacionListado().getPublicacionesAmigos(email);
	}

	@Override
	public void save(Publicacion p) throws EntityAlreadyExistsException {
		
		new PublicacionAlta().save(p);
		
	}

	@Override
	public void update(Publicacion p) throws EntityNotFoundException {
		
	}

	@Override
	public void delete(String email) throws Exception {
		new PublicacionBorrado().delete(email);
		
		
	}

	@Override
	public Publicacion findById(Long id) throws Exception {

		return new PublicacionBuscar().findById(id);
	}
}
