package impl.tew.persistence;

import com.tew.persistence.AmigosDao;
import com.tew.persistence.PersistenceFactory;
import com.tew.persistence.PublicacionDao;
import com.tew.persistence.UsuariosDao;

public class SimplePersistenceFactory implements PersistenceFactory {

	@Override
	public UsuariosDao createUsuariosDao() {
		
		return new UsuariosJdbcDao();
	}

	@Override
	public PublicacionDao createPublicacionDao() {
	
		return new PublicacionJdbcDao();
	}

	@Override
	public AmigosDao createAmigosDao() {
		
		return new AmigosJdbcDao();
	}

}
