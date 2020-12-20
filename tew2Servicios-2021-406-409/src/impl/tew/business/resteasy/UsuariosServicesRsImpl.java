package impl.tew.business.resteasy;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.business.resteasy.UsuariosServicesRs;
import com.tew.model.Amigos;
import com.tew.model.Usuarios;

import impl.tew.business.classes.UsuariosOperaciones;

public class UsuariosServicesRsImpl implements UsuariosServicesRs {

	@Override
	public List<Usuarios> getUsuarios() {
	
		try {
			return new UsuariosOperaciones().getUsuarios();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void delete(String email) throws EntityNotFoundException {
		new UsuariosOperaciones().delete(email);
	}

	@Override
	public void save(Usuarios u) throws EntityAlreadyExistsException {
		new UsuariosOperaciones().save(u);
	}

	@Override
	public void update(Usuarios usu) throws EntityNotFoundException {
		// TODO Auto-generated method stub
	}

	@Override
	public void guardarAmigo(Amigos email) throws EntityAlreadyExistsException {
	
		new UsuariosOperaciones().guardarAmigo(email);
		
	}

	@Override
	public void reinicioBaseDatos() {
		try {
			new UsuariosOperaciones().reiniciaBD();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Usuarios> getUsuarios2(String filtro, String email){
		String filt = filtro.substring(1,filtro.length()-1);
		try {
			return new UsuariosOperaciones().getUsuarios(filt,email);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Usuarios findByEmail(String email) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		try {
			return new UsuariosOperaciones().findByEmail(email);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Usuarios usuariosFindByEmail(String email) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuarios> getUsuarios(String filtro, String email) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
