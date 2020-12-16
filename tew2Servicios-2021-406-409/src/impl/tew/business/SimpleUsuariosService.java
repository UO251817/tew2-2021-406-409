package impl.tew.business;

import java.util.List;

import com.tew.business.UsuariosService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Amigos;
import com.tew.model.Usuarios;

import impl.tew.business.classes.UsuariosOperaciones;

public class SimpleUsuariosService implements UsuariosService {

	@Override
	public List<Usuarios> getUsuarios() throws Exception {
		
		return new UsuariosOperaciones().getUsuarios();
	}
	
	@Override
	public List<Usuarios> getUsuarios(String filtro, String email) {
		// TODO Auto-generated method stub
		return new UsuariosOperaciones().getUsuarios(filtro,email);
	}


	@Override
	public void save(Usuarios u) throws EntityAlreadyExistsException {
		
		new UsuariosOperaciones().save(u);
		
	}
	@Override
	public void guardarAmigo(Amigos nuevoAmigo) throws EntityAlreadyExistsException {
		
		new UsuariosOperaciones().guardarAmigo(nuevoAmigo);
		
	}

	@Override
	public void delete(String email) throws EntityNotFoundException {
		
		new UsuariosOperaciones().delete(email);
		
	}

	@Override
	public void reinicioBaseDatos() throws Exception {
		
		new UsuariosOperaciones().reiniciaBD();
		
	}

	@Override
	public Usuarios usuariosFindByEmail(String email) throws EntityNotFoundException {
		
		return new UsuariosOperaciones().findByEmail(email);
	}
}
