package impl.tew.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.tew.model.Publicacion;
import com.tew.persistence.PublicacionDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;
import com.tew.persistence.exception.PersistenceException;

public class PublicacionJdbcDao implements PublicacionDao{

	@Override
	public List<Publicacion> getPublicaciones() {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		List<Publicacion> publicaciones = new ArrayList<Publicacion>();

		try {

			String SQL_DRV  = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);

			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from Publicacion");
			rs = ps.executeQuery();

			while(rs.next()) {

				Publicacion publicacion = new Publicacion();
				publicacion.setID(rs.getLong("ID"));
				publicacion.setEmail(rs.getString("EMAIL"));
				publicacion.setTitulo(rs.getString("TITULO"));
				publicacion.setTexto(rs.getString("TEXTO"));
				publicacion.setFecha(rs.getLong("FECHA"));

				publicaciones.add(publicacion);

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

		return publicaciones;
	}



	int cont =100;

	@Override
	public void save(Publicacion p) throws AlreadyPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";
			


			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");		
			ps = con.prepareStatement("insert into PUBLIC.PUBLICACION ( ID, EMAIL, TITULO, TEXTO, FECHA) values ( DEFAULT, ?, ?, ?, ?)");
			ps.setString(1, p.getEmail());
			ps.setString(2, p.getTitulo());
			ps.setString(3, p.getTexto());
			ps.setLong(4, p.getFecha());
			

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("Publicacion " + p + " already persisted");
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
	public Publicacion findById(Long id) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Publicacion publicacion = null;

		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";


			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from PUBLICACION where ID = ?");
			ps.setLong(1, id);

			rs = ps.executeQuery();
			if (rs.next()) {
				publicacion = new Publicacion();

				publicacion.setID(rs.getLong("ID"));
				publicacion.setEmail(rs.getString("EMAIL"));
				publicacion.setTitulo(rs.getString("TITULO"));
				publicacion.setTexto(rs.getString("TEXTO"));
				publicacion.setFecha(rs.getLong("FECHA"));

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

		return publicacion;
	}



	@Override
	public List<Publicacion> getPublicaciones(String email_usuario) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		List<Publicacion> publicaciones = new ArrayList<Publicacion>();

		try {

			String SQL_DRV  = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);

			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from PUBLICACION where EMAIL = ?");
			ps.setString(1, email_usuario);

			rs = ps.executeQuery();

			while(rs.next()) {
				
				Date fecha_ok = new Date(rs.getLong("FECHA"));
				SimpleDateFormat fecha_format = new SimpleDateFormat("dd/MM/yyyy");
								
				String Fecha=fecha_format.format(fecha_ok);

				Publicacion publicacion = new Publicacion();
				publicacion.setID(rs.getLong("ID"));
				publicacion.setEmail(rs.getString("EMAIL"));
				publicacion.setTitulo(rs.getString("TITULO"));
				publicacion.setTexto(rs.getString("TEXTO"));
				publicacion.setFecha_legible(Fecha);
				
				publicaciones.add(publicacion);

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

		return publicaciones;
	}
	
	
	@Override
	public List<Publicacion> getPublicacionesAmigos(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		List<Publicacion> publicaciones = new ArrayList<Publicacion>();

		try {

			String SQL_DRV  = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);

			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("SELECT * FROM PUBLIC.PUBLICACION WHERE EMAIL IN (SELECT EMAIL_AMIGO FROM PUBLIC.AMIGOS WHERE EMAIL_USUARIO= ? AND ACEPTADA='1') OR EMAIL= ?");
			ps.setString(1, email);
			ps.setString(2, email);

			rs = ps.executeQuery();

			while(rs.next()) {
				
				Date fecha_ok = new Date(rs.getLong("FECHA"));
				SimpleDateFormat fecha_format = new SimpleDateFormat("dd/MM/yyyy");
								
				String Fecha=fecha_format.format(fecha_ok);
	

				Publicacion publicacion = new Publicacion();
				publicacion.setID(rs.getLong("ID"));
				publicacion.setEmail(rs.getString("EMAIL"));
				publicacion.setTitulo(rs.getString("TITULO"));
				publicacion.setTexto(rs.getString("TEXTO"));
				publicacion.setFecha_legible(Fecha);
				
				publicaciones.add(publicacion);

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

		return publicaciones;
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
			ps = con.prepareStatement("delete from publicacion where EMAIL = ?");

			ps.setString(1, email);

			rows = ps.executeUpdate();
			if (rows < 0) {
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
}