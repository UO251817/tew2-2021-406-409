package impl.tew.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.tew.model.Amigos;
import com.tew.persistence.AmigosDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;
import com.tew.persistence.exception.PersistenceException;

public class AmigosJdbcDao implements AmigosDao {

	@Override
	public List<Amigos> getAmigos() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		List<Amigos> amigos = new ArrayList<Amigos>();

		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);

			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from Amigos");
			rs = ps.executeQuery();

			while (rs.next()) {

				Amigos amigo = new Amigos();

				amigo.setEmail_usuario(rs.getString("EMAIL_USUARIO"));
				amigo.setEmail_amigo(rs.getString("EMAIL_AMIGO"));
				amigo.setAceptada(rs.getBoolean("ACEPTADA"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) { try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}

		return amigos;
	}

	@Override
	public List<Amigos> getAmigos(String email_usuario) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		List<Amigos> amigos = new ArrayList<Amigos>();

		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);

			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from Amigos where EMAIL_AMIGO = ? and ACEPTADA = '0' ");
			ps.setString(1, email_usuario);

			rs = ps.executeQuery();

			while (rs.next()) {

				Amigos amigo = new Amigos();

				amigo.setEmail_usuario(rs.getString("EMAIL_USUARIO"));
				amigo.setEmail_amigo(rs.getString("EMAIL_AMIGO"));
				amigo.setAceptada(rs.getBoolean("ACEPTADA"));

				amigos.add(amigo);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}

		return amigos;
	}

	@Override
	public void save(Amigos a) throws AlreadyPersistedException {

		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;

		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(
					"insert into PUBLIC.AMIGOS (EMAIL_USUARIO, EMAIL_AMIGO, ACEPTADA) " + "values (?, ?, ?)");

			ps.setString(2, a.getEmail_usuario());
			ps.setString(3, a.getEmail_amigo());
			ps.setBoolean(3, a.isAceptada());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("Amigo " + a + " already persisted");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}
	}

	@Override
	public Amigos findByEmail(String email_usuario) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Amigos amigo = null;

		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from amigos where EMAIL_USUARIO = ?");
			ps.setString(1, email_usuario);

			rs = ps.executeQuery();
			if (rs.next()) {
				amigo = new Amigos();

				amigo.setEmail_usuario(rs.getString("EMAIL_USUARIO"));
				amigo.setEmail_amigo(rs.getString("EMAIL_AMIGO"));
				amigo.setAceptada(rs.getBoolean("ACEPTADA"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception ex) {
				}
			}
			;
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}

		return amigo;
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
			ps = con.prepareStatement("delete from amigos where EMAIL_USUARIO = ? or EMAIL_AMIGO=?");

			ps.setString(1, email);
			ps.setString(2, email);

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
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
		}
	}

	@Override
	public void aceptar(String email_usuario, String email_amigo) throws NotPersistedException {

		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		Connection con = null;
		int rows = 0;

		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(
					"UPDATE PUBLIC.AMIGOS SET ACEPTADA='1' WHERE EMAIL_USUARIO=? AND EMAIL_AMIGO = ? AND ACEPTADA='0'");

			ps.setString(1, email_usuario);
			ps.setString(2, email_amigo);

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Usuario " + email_usuario + " not found");
			}

			ps2 = con.prepareStatement("insert into PUBLIC.AMIGOS (EMAIL_USUARIO, EMAIL_AMIGO, ACEPTADA) values ( ?, ?, '1')");

			ps2.setString(1, email_amigo);
			ps2.setString(2, email_usuario);

			rows = ps2.executeUpdate();

			if (rows != 1) {
				throw new NotPersistedException("Usuario " + email_usuario + " not found");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}
	}

	@Override
	public List<Amigos> getCandidatos(String email_usuario) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		List<Amigos> amigos = new ArrayList<Amigos>();

		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);

			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from usuarios where email not in (select email_amigo from amigos where email_usuario = ?) "
                    + " and email <> ? and (email like ? or nombre like ?)");
			ps.setString(1, email_usuario);
			ps.setString(2, email_usuario);
			ps.setString(3, email_usuario);
			ps.setString(4, email_usuario);

			rs = ps.executeQuery();

			while (rs.next()) {

				Amigos amigo = new Amigos();

				amigo.setEmail_usuario(rs.getString("EMAIL_USUARIO"));
				amigo.setEmail_amigo(rs.getString("EMAIL_AMIGO"));
				amigo.setAceptada(rs.getBoolean("ACEPTADA"));

				amigos.add(amigo);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}

		return amigos;
	}
}