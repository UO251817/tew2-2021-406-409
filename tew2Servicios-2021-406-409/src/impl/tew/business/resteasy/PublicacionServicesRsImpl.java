package impl.tew.business.resteasy;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.business.resteasy.PublicacionServicesRs;
import com.tew.model.Publicacion;
import impl.tew.business.classes.PublicacionAlta;
import impl.tew.business.classes.PublicacionBorrado;
import impl.tew.business.classes.PublicacionBuscar;
import impl.tew.business.classes.PublicacionListado;


public class PublicacionServicesRsImpl implements PublicacionServicesRs{

	@Override
	public List<Publicacion> getPublicaciones() {
		
		try {
			return new PublicacionListado().getPublicaciones();
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Publicacion> getPublicaciones(String email) throws EntityNotFoundException {
		
		try {
			return new PublicacionListado().getPublicaciones(email);
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Publicacion> getPublicacionesAmigos(String email) {
		

		try {
			return new PublicacionListado().getPublicacionesAmigos(email);
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Publicacion findById(Long id) throws EntityNotFoundException {
		try {
			return new PublicacionBuscar().findById(id);
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void update(Publicacion p) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Publicacion p) throws EntityAlreadyExistsException {
		
		new PublicacionAlta().save(p);
		
	}

	@Override
	public void delete(String email) throws EntityNotFoundException {
		

		try {
			new PublicacionBorrado().delete(email);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
