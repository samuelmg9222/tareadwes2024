package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Credenciales;
import modelo.Persona;
import utilidades.ConexionBD;

public class  CredencialesDAO {
	
	
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public CredencialesDAO(Connection c) {
		this.con = c;
	}

	  public List<Credenciales> verCredenciales() {
	        List<Credenciales> Credenciales = new ArrayList<>();
	        String sql = "SELECT * FROM credenciales";

	        try {
				if( this.con ==null ||this.con.isClosed()) 
					   this.con=ConexionBD.getInstance().getConnection();
	            ps = con.prepareStatement(sql);
	            rs = ps.executeQuery();

	            while (rs.next()) {
	                Long id = rs.getLong("id");
	                String usuario = rs.getString("usuario");
	                String password = rs.getString("password");
	                Long idpersona = rs.getLong("idpersona");
	                Credenciales credenciales = new Credenciales(id,usuario,password,idpersona);

	                Credenciales.add(credenciales);
	            }

	        } catch (SQLException e) {
	            System.err.println("Error haciendo el listado de credenciales --> " + e.getMessage());
	        }

	        return Credenciales;
	    }

	 
	    public Credenciales findByUsuario(String user) {
	        Credenciales credenciales = new Credenciales();
	        String sql = "SELECT * FROM credenciales WHERE usuario = ?";

	        try {
				if (this.con == null || this.con.isClosed())
					this.con = ConexionBD.getInstance().getConnection();
	            ps = con.prepareStatement(sql);
	            ps.setString(1, user);
	            rs = ps.executeQuery();

	            if (rs.next()) {
	                Long id = rs.getLong("id");
	                String usuario = rs.getString("usuario");
	                String password = rs.getString("password");
	                Long idPersona = rs.getLong("idpersona");
	                credenciales.setId(id);
	                credenciales.setUsuario(usuario);
	                credenciales.setPassword(password);
	                credenciales.setIdpersona(idPersona);
	            }
	            ps.close();
	            rs.close();
	            ConexionBD.cerrarConexion();
	        } catch (SQLException e) {
	            System.err.println("Error " + e.getMessage());
	        }

	        return credenciales;
	    }
	    

	    public boolean insertarcredenciales(Credenciales credenciales) {
	        String sql = "INSERT INTO credenciales (usuario, password, idpersona) VALUES (?, ?, ?)";

	        try {
	            ps = con.prepareStatement(sql);
	            ps.setString(1, credenciales.getUsuario());
	            ps.setString(2, credenciales.getPassword());
	            ps.setLong(3, credenciales.getIdpersona());
	           ps.executeUpdate();

	            return true;

	        } catch (SQLException e) {
	            System.err.println("Error al guardar la credencial --> " + e.getMessage());
	            return false;
	        }
	        
	    }
	        
	      
	        

	    
	    
	    
	    
	    
	    
}