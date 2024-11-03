package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import principal.ConexionBD;

public class PersonaDAO {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public PersonaDAO(Connection c) {
		this.con = c;
	}
	

	public  boolean existeUsuario(String cod) {
		boolean existe = false;
	
		try {
			if( this.con ==null ||this.con.isClosed()) 
				   this.con=ConexionBD.getInstance().getConnection();
		
	    String consulta = "SELECT COUNT(*) FROM personas WHERE id = ?";
	    

	   
	    	 ps = con.prepareStatement(consulta);
	        ps.setString(1, cod);
	        rs = ps.executeQuery();
	        
	            if (rs.next() && rs.getInt(1) > 0) {
	                existe = true;
	                ps.close();
		            ConexionBD.cerrarConexion();
	            }
	        

	    } catch (SQLException e) {
	        System.err.println("Error al consultar si el código ya existe: " + e.getMessage());
	    }

	    return existe;
	}
	
	
	public void nuevoUsuarioPersonal(Long id, String contraseña, String email) {
	
	
	
	
	
	
}}
 