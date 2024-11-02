package utilidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import principal.ConexionBD;

public class Utils {
	
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	 
	    public Utils(Connection c) {
	        this.con = c;
	    }
	public long generarIdEjemplar() {
		 try {
				if( this.con ==null ||this.con.isClosed()) 
					   this.con=ConexionBD.getInstance().getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    long id = 0;
	    String sql = "SELECT COALESCE(MAX(id), 0) + 1 AS nuevo_id FROM ejemplares";
	    
	    try (PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {
	        
	       
	        if (rs.next()) {
	            id = rs.getLong("nuevo_id");
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al generar un nuevo ID: " + e.getMessage());
	        e.printStackTrace();
	  
	    }
	    return id;
	}
	public long generarIdMensaje() {
		 try {
				if( this.con ==null ||this.con.isClosed()) 
					   this.con=ConexionBD.getInstance().getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    long id = 0;
	    String sql = "SELECT COALESCE(MAX(id), 0) + 1 AS nuevo_id FROM mensajes";
	    
	    try (PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {
	        
	        
	        if (rs.next()) {
	            id = rs.getLong("nuevo_id");
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al generar un nuevo ID: " + e.getMessage());
	        e.printStackTrace();
	  
	    }
	    return id;
	}

public String generarNombreEjemplar(String codigo) {
	 try {
			if( this.con ==null ||this.con.isClosed()) 
				   this.con=ConexionBD.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	String nombreEjemplar = null;
	
	  String sql ="SELECT nombrecomun FROM PLANTAS WHERE CODIGO=?";
	  try { 
		  ps = con.prepareStatement(sql);
	  ps.setString(1, codigo); 
      rs = ps.executeQuery();
      
    if (rs.next()) {
    	nombreEjemplar = rs.getString("nombrecomun");
    }
    
} catch (SQLException e) {
    System.err.println("Error al generar un nuevo ID: " + e.getMessage());
    e.printStackTrace();
}
	    
	 
	
	return nombreEjemplar;
}
public static String obtenerEncabezado() {
    return String.format("%-10s %-20s %-30s", "Código", "Nombre Común", "Nombre Científico");
}


public  boolean existeCodigoPlanta(String codigo) {
	try {
		if( this.con ==null ||this.con.isClosed()) 
			   this.con=ConexionBD.getInstance().getConnection();
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
    String consulta = "SELECT COUNT(*) FROM plantas WHERE codigo = ?";
    boolean existe = false;

    try  {
    	 ps = con.prepareStatement(consulta);
        ps.setString(1, codigo);

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
public  boolean existeCodigoEjemplar(String codigo) {
	try {
		if( this.con ==null ||this.con.isClosed()) 
			   this.con=ConexionBD.getInstance().getConnection();
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
    String consulta = "SELECT COUNT(*) FROM ejemplares WHERE id = ?";
    boolean existe = false;

    try  {
    	 ps = con.prepareStatement(consulta);
        ps.setString(1, codigo);

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
public String generarmensaje() {
	 LocalDateTime fechaHora = LocalDateTime.now();
	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	String mensaje="Fecha: "+fechaHora.format(formatter)+" usuario:";
	
	
	
	return mensaje;
	
}

}




