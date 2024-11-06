package principal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;


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
	    
	  


	    private PlantaDAO plantaDAO = null;
	    public PlantaDAO getPlantaDAO() {
	        
			if (plantaDAO == null) {
	            plantaDAO = new PlantaDAO(getConnection()); 
	        }
	        return plantaDAO;
	    }
	    private EjemplarDAO ejemplarDAO = null;
	    public EjemplarDAO getEjemplarDAO() {
	        
			if (ejemplarDAO == null) {
				ejemplarDAO = new EjemplarDAO(getConnection()); 
	        }
	        return ejemplarDAO;
	    }
	    private MensajeDAO mensajeDAO = null;
	    public MensajeDAO getMensajeDAO() {
	        
		 	if (mensajeDAO == null) {
				mensajeDAO = new MensajeDAO(getConnection()); 
	        }
	        return mensajeDAO;
	    }
	    private PersonaDAO personaDAO = null;
	    public PersonaDAO getPersonaDAO() {
	        
			if (personaDAO == null) {
				personaDAO = new PersonaDAO(getConnection()); 
	        }
	        return personaDAO;
	    }


	    private CredencialesDAO credencialesDAO = null;
		public CredencialesDAO getCredencialesDAO() {
			if (credencialesDAO == null) {
				credencialesDAO = new CredencialesDAO(getConnection()); 
	        }
	        return credencialesDAO;
		
		}
}