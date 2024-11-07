package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Persona;
import modelo.Planta;
import utilidades.ConexionBD;

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
	                
	            }
	            ps.close();
	            ConexionBD.cerrarConexion();

	    } catch (SQLException e) {
	        System.err.println("Error al consultar si el código ya existe: " + e.getMessage());
	    }

	    return existe;
	}
	
	
	public void nuevoUsuarioPersonal(Long id, String contraseña, String email) {
	
	
	
	
	
	
}
	
	
	 public List<Persona> findAll(){
		   List<Persona> Personas = new ArrayList<>();
		   try {
				if( this.con ==null ||this.con.isClosed()) 
					   this.con=ConexionBD.getInstance().getConnection();
			
	        
	        String sql = "SELECT * FROM personas";

	       
	             ps = con.prepareStatement(sql);
	             rs = ps.executeQuery();

	            while (rs.next()) {
	                long id = rs.getLong("id");
	                String nombre= rs.getString("email");
	                String email = rs.getString("nombre");

	                Persona persona = new Persona(id, nombre, email);
	                Personas.add(persona);
	               
	            } 
	          
	            ConexionBD.cerrarConexion();
	            ps.close();
	            rs.close();
	        } catch (SQLException e) {
	            System.err.println("Error: " + e.getMessage());
	            e.printStackTrace();
	        }
	       
	    return Personas;
	   }
	public String obtenerNombreUsuario(Long id) {
		
		 String nombre = null;
	
		try {
			if( this.con ==null ||this.con.isClosed()) 
				   this.con=ConexionBD.getInstance().getConnection();
		
	    String consulta = "SELECT nombre FROM personas WHERE id = ?";
	   

	   
	    	 ps = con.prepareStatement(consulta);
	        ps.setLong(1, id);
	       rs = ps.executeQuery();
	        
	       if (rs.next()) {
	            nombre = rs.getString("nombre");
	        }
	                ps.close();
	                rs.close();
		            ConexionBD.cerrarConexion();
	            
	        

	    } catch (SQLException e) {
	        System.err.println("Error al consultar si el código ya existe: " + e.getMessage());
	    }
		return nombre;



}
	
	public Persona obtenerDatosPersona(Long id) {
		
	
	Persona persona =new Persona();
		try {
			if( this.con ==null ||this.con.isClosed()) 
				   this.con=ConexionBD.getInstance().getConnection();
		
	    String consulta = "SELECT Persona.id, Persona.nombre, Persona.email FROM Persona JOIN Credenciales ON Persona.id = Credenciales.id_persona WHERE Credenciales.id_persona = ?";
	   

	   
	    	 ps = con.prepareStatement(consulta);
	        ps.setLong(1, id);
	       rs = ps.executeQuery();
	        
	       if (rs.next()) {
	    	   Long personaId = rs.getLong("id");
	           String nombre = rs.getString("nombre");
	           String email = rs.getString("email");

	           
	           persona = new Persona(personaId, nombre, email);}
	                ps.close();
	                rs.close();
		            ConexionBD.cerrarConexion();
	            
	        

	    } catch (SQLException e) {
	        System.err.println("Error al consultar si el código ya existe: " + e.getMessage());
	    }
		return persona;
	
	
	
}}