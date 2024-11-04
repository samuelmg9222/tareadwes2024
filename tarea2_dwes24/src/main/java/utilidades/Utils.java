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
	
	


public static String obtenerEncabezado() {
    return String.format("%-10s %-20s %-30s", "Código", "Nombre Común", "Nombre Científico");
}




public String generarmensaje() {
	 LocalDateTime fechaHora = LocalDateTime.now();
	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	String mensaje="Fecha: "+fechaHora.format(formatter)+" usuario:";
	
	
	
	return mensaje;
	
}



}




