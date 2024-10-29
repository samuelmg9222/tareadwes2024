package principal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

import DAOImp.CredencialesDAOImpl;
import DAOImp.MensajeDAOImpl;
import DAOImp.PersonaDAOImpl;

import dao.*;

public class ConexionBD {
	private Connection con;
	
	private static ConexionBD f;
	
	private ConexionBD() {
		Properties prop = new Properties();
		MysqlDataSource m = new MysqlDataSource();
		FileInputStream fis;
		 
	
	
	try {
		fis = new FileInputStream("src/main/resources/db.properties");
		prop.load(fis);
		m.setUrl(prop.getProperty("url"));
		m.setPassword(prop.getProperty("password"));
		m.setUser(prop.getProperty("usuario"));
		con = m.getConnection();
		
	} catch (FileNotFoundException e) {
		System.out.println("Error al acceder al fichero properties " + e.getMessage());
	} catch (IOException e) {
		System.out.println("Error al leer las propiedades del fichero properties" + e.getMessage());
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
}

/*
	public EjemplarDAO getEjemplarDAO() {
		return new EjemplarDAOImp(con);
	}

	public MensajeDAO getMensajeDAO() {
		return new MensajeDAOImpl(con);
	}

	public PlantaDAO getPlantaDAO() {
		return new PlantaDAOImpl(con);
	}

	public PersonaDAO getPersonaDAO() {
		return new PersonaDAOImpl(con);
	}
	public CredencialesDAO getCredencialesDAO() {
		return new CredencialesDAOImpl(con);
	}
*/

	  public static ConexionBD getInstance() {
	        
			try {
				if (f == null || f.getConnection().isClosed()) {
				    f = new ConexionBD();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return f;
	    }

	    
	    public  Connection getConnection() {
//	    	try {
//				if (con== null || con.isClosed()) {
//					 f = new ConexionBD();
//				}
//			} catch (SQLException e) {
//				System.out.println("Se ha producido una SQLException: " + e.getMessage());
//				e.printStackTrace();
//			}
	        return con;
	    }
	    
	    public static void cerrarConexion() {
			try {
				if (f.getConnection() != null && !f.getConnection().isClosed()) {
					f.getConnection().close();
				}
			} catch (SQLException e) {
				System.out.println("Se ha producido una SQLException: " + e.getMessage());
				e.printStackTrace();
			}
		}
}