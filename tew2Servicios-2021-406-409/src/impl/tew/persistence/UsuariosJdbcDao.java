package impl.tew.persistence;


import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.tew.model.Amigos;
import com.tew.model.Usuarios;
import com.tew.persistence.UsuariosDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;
import com.tew.persistence.exception.PersistenceException;

public class UsuariosJdbcDao implements UsuariosDao{

	@Override
	public List<Usuarios> getUsuarios() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		List<Usuarios> usuarios = new ArrayList<Usuarios>();

		try {

			String SQL_DRV  = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);

			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from usuarios");
			rs = ps.executeQuery();

			while(rs.next()) {
				Usuarios usuario = new Usuarios();
				usuario.setEmail(rs.getString("EMAIL"));
				usuario.setPasswd(rs.getString("PASSWD"));
				usuario.setRol(rs.getString("ROL"));
				usuario.setNombre(rs.getString("NOMBRE"));

				usuarios.add(usuario);
			}
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}

		return usuarios;
	}
	
	
	@Override
	public List<Usuarios> getUsuarios(String filtro, String email) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		List<Usuarios> usuarios = new ArrayList<Usuarios>();

		try {

			String SQL_DRV  = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);

			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from usuarios where email not in (select email_amigo from amigos where email_usuario = ? ) and email <> ? and (email like ? or nombre like ?)");
			ps.setString(1, email);
			ps.setString(2, email);
			ps.setString(3, "%"+filtro+"%");
			ps.setString(4, "%"+filtro+"%");
			
			rs = ps.executeQuery();

			while(rs.next()) {
				Usuarios usuario = new Usuarios();
				usuario.setEmail(rs.getString("EMAIL"));
				usuario.setPasswd(rs.getString("PASSWD"));
				usuario.setRol(rs.getString("ROL"));
				usuario.setNombre(rs.getString("NOMBRE"));

				usuarios.add(usuario);
			}
			
			System.out.println(usuarios);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		return usuarios;	
	}

	@Override
	public void save(Usuarios u) throws AlreadyPersistedException {

		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;

		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(	"insert into PUBLIC.USUARIOS (EMAIL, PASSWD, ROL, NOMBRE) " +	"values (?, ?, ?, ?)");

			ps.setString(1, u.getEmail());
			ps.setString(2, u.getPasswd());
			ps.setString(3, u.getRol());
			ps.setString(4, u.getNombre());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("Usuario " + u + " already persisted");
			} 

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
	}
	
	

	@Override
	public void guardarAmigo(Amigos nuevoAmigo) throws AlreadyPersistedException {
		
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;

		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(	"insert into PUBLIC.AMIGOS (EMAIL_USUARIO, EMAIL_AMIGO, ACEPTADA) " +	"values ( ?, ?, ?)");

			ps.setString(1, nuevoAmigo.getEmail_usuario());
			ps.setString(2, nuevoAmigo.getEmail_amigo());
			ps.setBoolean(3, nuevoAmigo.isAceptada());
			

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("Usuario " + nuevoAmigo + " already persisted");
			} 

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}		
	}

	
	@Override
	public void delete(String email) throws NotPersistedException {

		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;

		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("delete from usuarios where EMAIL = ?");

			ps.setString(1, email);

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Usuario " + email + " not found");
			} 

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}


	}
	
	@Override
	public void reset() {
		ejecutaSQL("DELETE FROM AMIGOS");
		ejecutaSQL("DELETE FROM PUBLICACION");
		ejecutaSQL("DELETE FROM USUARIOS");
		ejecutaSQL("INSERT INTO USUARIOS VALUES('admin@dominio.com', 'admin', 'admin', 'admin')");
		
		JSONParser parser= new JSONParser();
		try {

			Object obj = parser.parse(new FileReader("/WebContent/redsocial.json"));

			JSONObject jsonObject = (JSONObject) obj;

			//recorrer usuarios
			JSONArray usus = (JSONArray) jsonObject.get("usuarios");
			Iterator<?> iusu = usus.iterator();
			while(iusu.hasNext()) {
				JSONObject actualusu = (JSONObject) iusu.next();
				String email = (String) actualusu.get("email");
				String password = (String) actualusu.get("passwd");
				String rol = (String) actualusu.get("rol");
				String nombre = (String) actualusu.get("nombre");
				ejecutaSQL("INSERT INTO VALUES('" + email + "','" + password + "','" + rol + "','" + nombre +"')");
			}

			//recorrer publicaciones
			JSONArray publis = (JSONArray) jsonObject.get("publicaciones");
			Iterator<?> ipublis = publis.iterator();
			while(ipublis.hasNext()) {
				JSONObject actualpubli = (JSONObject) ipublis.next();
				String email = (String) actualpubli.get("email");
				String titulo = (String) actualpubli.get("titulo");
				String texto = (String) actualpubli.get("texto");
				Long fecha = Long.parseLong((String)actualpubli.get("fecha"));
				ejecutaSQL("INSERT INTO VALUES('" + email + "','" + titulo + "','" + texto + "'," + fecha +")");
			}

			//recorrer amigos
			JSONArray amis = (JSONArray) jsonObject.get("amigos");
			Iterator<?> iamis = amis.iterator();
			while(iamis.hasNext()) {
				JSONObject actualami = (JSONObject) iamis.next();
				String email_usuario = (String)  actualami.get("emailusuario");
				String email_amigo = (String) actualami.get("emailamigo");
				ejecutaSQL("INSERT INTO VALUES('" + email_usuario + "','" + email_amigo + "', '1')");
				ejecutaSQL("INSERT INTO VALUES('" + email_amigo + "','" + email_usuario + "', '1')");
			}
		}catch(Exception e) {
			System.err.println("Error: " + e.toString());
		}
	}

	/*
	@Override
	public void reset() {

		Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		session.clear();

		ejecutaSQL("DELETE FROM AMIGOS");
		ejecutaSQL("DELETE FROM PUBLICACION");
		ejecutaSQL("DELETE FROM USUARIOS");
		ejecutaSQL("INSERT INTO USUARIOS VALUES('admin@email.com','admin','administrador','admin1')");
		ejecutaSQL("INSERT INTO USUARIOS VALUES('user1@email.com','user1','usuario','user1')");
		ejecutaSQL("INSERT INTO USUARIOS VALUES('user2@email.com','user2','usuario','user2')");
		ejecutaSQL("INSERT INTO USUARIOS VALUES('user3@email.com','user3','usuario','user3')");
		ejecutaSQL("INSERT INTO USUARIOS VALUES('user4@email.com','user4','usuario','user4')");
		ejecutaSQL("INSERT INTO USUARIOS VALUES('user5@email.com','user5','usuario','user5')");
		ejecutaSQL("INSERT INTO USUARIOS VALUES('user6@email.com','user6','usuario','user6')");
		ejecutaSQL("INSERT INTO USUARIOS VALUES('user7@email.com','user7','usuario','user7')");
		ejecutaSQL("INSERT INTO USUARIOS VALUES('user8@email.com','user8','usuario','user8')");
		ejecutaSQL("INSERT INTO USUARIOS VALUES('user9@email.com','user9','usuario','user9')");
		ejecutaSQL("INSERT INTO PUBLICACION VALUES(0,'user1@email.com','Hola','Me presento, soy el user1',452352151)");
		ejecutaSQL("INSERT INTO PUBLICACION VALUES(1,'user1@email.com','Hola2','Yo la verdad que estoy bien',123225154)");
		ejecutaSQL("INSERT INTO PUBLICACION VALUES(2,'user1@email.com','Hola3','Aunque podr tar mejor',5145145145)");
		ejecutaSQL("INSERT INTO PUBLICACION VALUES(3,'user2@email.com','Hola','Me presento, soy el user2 ',2343425)");
		ejecutaSQL("INSERT INTO PUBLICACION VALUES(4,'user2@email.com','Hola2','Yo la verdad que estoy bien',8181818111)");
		ejecutaSQL("INSERT INTO PUBLICACION VALUES(5,'user2@email.com','Hola3','Aunque podia estar mejor',4363623236)");
		ejecutaSQL("INSERT INTO PUBLICACION VALUES(6,'user3@email.com','Hola','Me presento, soy el user3 ',3453464632563)");
		ejecutaSQL("INSERT INTO PUBLICACION VALUES(7,'user3@email.com','Hola2','Yo la verdad que estoy bien',34523636346)");
		ejecutaSQL("INSERT INTO PUBLICACION VALUES(8,'user3@email.com','Hola3','Aunque pueda estar mejor',5363476737373)");
		ejecutaSQL("INSERT INTO PUBLICACION VALUES(9,'user4@email.com','Hola','Me presento, soy el user4 ',4563467743683)");
		ejecutaSQL("INSERT INTO PUBLICACION VALUES(10,'user4@email.com','Hola2','Yo la verdad que estoy bien',473568356836)");
		ejecutaSQL("INSERT INTO PUBLICACION VALUES(11,'user4@email.com','Hola3','Aunque podr\u00eda estar mejor ',63734673467634)");
		ejecutaSQL("INSERT INTO PUBLICACION VALUES(12,'user5@email.com','Hola','Me presento, soy el user5 ',346734673647)");
		ejecutaSQL("INSERT INTO PUBLICACION VALUES(13,'user5@email.com','Hola2','Yo la verdad que estoy bien',4572457574)");
		ejecutaSQL("INSERT INTO PUBLICACION VALUES(14,'user5@email.com','Hola3','Aunque podr\u00eda estar mejor',43734545737)");
		ejecutaSQL("INSERT INTO PUBLICACION VALUES(15,'user6@email.com','Hola','Me presento, soy el user6',4567475788)");
		ejecutaSQL("INSERT INTO PUBLICACION VALUES(16,'user6@email.com','Hola2','Yo la verdad que estoy bien',435474368364)");
		ejecutaSQL("INSERT INTO PUBLICACION VALUES(17,'user6@email.com','Hola3','Aunque podr\u00eda estar mejor ',435235623)");
		ejecutaSQL("INSERT INTO PUBLICACION VALUES(18,'user7@email.com','Hola','Me presento, soy el user7',3452324525355)");
		ejecutaSQL("INSERT INTO PUBLICACION VALUES(19,'user7@email.com','Hola2','Yo la verdad que estoy bien',3245235235435)");
		ejecutaSQL("INSERT INTO PUBLICACION VALUES(20,'user7@email.com','Hola3','Aunque podr\u00eda estar mejor ',32452535425)");
		ejecutaSQL("INSERT INTO PUBLICACION VALUES(21,'user8@email.com','Hola','Me presento, soy el user8',2345353453245)");
		ejecutaSQL("INSERT INTO PUBLICACION VALUES(22,'user8@email.com','Hola2','Yo la verdad que estoy bien',34532445345)");
		ejecutaSQL("INSERT INTO PUBLICACION VALUES(23,'user8@email.com','Hola3','Aunque podr\u00eda estar mejor ',3425234534532)");
		ejecutaSQL("INSERT INTO PUBLICACION VALUES(24,'user9@email.com','Hola','Me presento, soy el user9',3252452345)");
		ejecutaSQL("INSERT INTO PUBLICACION VALUES(25,'user9@email.com','Hola2','Yo la verdad que estoy bien',325324545)");
		ejecutaSQL("INSERT INTO PUBLICACION VALUES(26,'user9@email.com','Hola3','Aunque podr\u00eda estar mejor',345245324525)");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user1@email.com','user2@email.com','1')");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user1@email.com','user3@email.com','1')");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user1@email.com','user4@email.com','0')");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user1@email.com','user8@email.com','1')");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user2@email.com','user1@email.com','1')");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user2@email.com','user5@email.com','0')");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user2@email.com','user6@email.com','1')");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user3@email.com','user1@email.com','1')");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user3@email.com','user7@email.com','1')");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user3@email.com','user8@email.com','0')");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user4@email.com','user1@email.com','1')");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user4@email.com','user5@email.com','0')");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user4@email.com','user9@email.com','1')");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user5@email.com','user2@email.com','1')");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user5@email.com','user4@email.com','0')");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user5@email.com','user9@email.com','1')");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user6@email.com','user2@email.com','1')");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user6@email.com','user7@email.com','1')");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user6@email.com','user9@email.com','0')");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user7@email.com','user3@email.com','1')");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user7@email.com','user6@email.com','0')");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user7@email.com','user8@email.com','1')");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user8@email.com','user1@email.com','0')");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user8@email.com','user3@email.com','1')");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user8@email.com','user7@email.com','1')");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user9@email.com','user4@email.com','1')");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user9@email.com','user5@email.com','0')");
		ejecutaSQL("INSERT INTO AMIGOS VALUES('user9@email.com','user6@email.com','0')");
		
		
		System.out.println("llego fin jdbc");
	}
*/	
	private void ejecutaSQL(String linea) {

		PreparedStatement ps = null;
		Connection con = null;
		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(linea);

			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}	
	}

	@Override
	public Usuarios findByEmail(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Usuarios usuario = null;

		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";


			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from USUARIOS where EMAIL = ?");
			ps.setString(1, email);

			rs = ps.executeQuery();
			if (rs.next()) {
				usuario = new Usuarios();

				usuario.setEmail(rs.getString("EMAIL"));
				usuario.setPasswd(rs.getString("PASSWD"));
				usuario.setRol(rs.getString("ROL"));
				usuario.setNombre(rs.getString("NOMBRE"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}

		return usuario;
	}
}
