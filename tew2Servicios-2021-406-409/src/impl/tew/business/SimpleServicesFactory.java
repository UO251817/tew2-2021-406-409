package impl.tew.business;

import com.tew.business.AmigosService;
import com.tew.business.LoginService;
import com.tew.business.PublicacionService;
import com.tew.business.ServicesFactory;
import com.tew.business.UsuariosService;

public class SimpleServicesFactory implements ServicesFactory {

	@Override
	public UsuariosService createUsuariosService() {
		
		return new SimpleUsuariosService();
	}

	@Override
	public PublicacionService createPublicacionService() {
	
		return new SimplePublicacionService();
	}

	@Override
	public AmigosService createAmigosService() {
		
		return new SimpleAmigosService();
	}

	@Override
	public LoginService createLoginService() {
		
		return new SimpleLoginService();
	}
}
