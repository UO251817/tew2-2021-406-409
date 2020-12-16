package impl.tew.business;

import java.util.List;

import com.tew.business.LoginService;
import com.tew.business.UsuariosService;
import com.tew.infraestructure.Factories;
import com.tew.model.Usuarios;

public class SimpleLoginService implements LoginService {

	Usuarios usuario;

	@Override
	public Usuarios verify(String login, String password) {

		if(!validLogin(login,password))
			return null;
		return usuario;
	}

	@Override
	public boolean validLogin(String login, String password) {

		UsuariosService usuarioService = Factories.services.createUsuariosService();

		String email = "";
		String contraseña = "";
		try {
			List<Usuarios> usuarios = usuarioService.getUsuarios();
			for(Usuarios u: usuarios) {
				if(u.getEmail().equals(login) && u.getPasswd().equals(password)) {
					email = u.getEmail();
					contraseña = u.getPasswd();
					usuario = u;
					u.setEmail(email);
					u.setPasswd(contraseña);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return email.equals(login) && contraseña.equals(password);
	}
}