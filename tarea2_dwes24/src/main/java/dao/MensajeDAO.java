package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import modelo.Mensaje;
import modelo.Planta;
import utilidades.ConexionBD;

public class MensajeDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public MensajeDAO(Connection c) {
		this.con = c;
	}

	public int nuevoMensaje(Mensaje m,Long idpersona) {
		int ins=0;
		try {
			if (this.con == null || this.con.isClosed())
				this.con = ConexionBD.getInstance().getConnection();

			String sqlIns2 = "INSERT INTO mensajes(id, fechahora, mensaje, idejemplar, idpersona) VALUES(?, ?, ?, ?, ?)";
			PreparedStatement ps2 = con.prepareStatement(sqlIns2);
			ps2.setLong(1, m.getId());
			 ps2.setTimestamp(2, Timestamp.valueOf(m.getFechaHora()));//Esto  sirve para insertar LocalDateTime con el formato dd-MM-yyyy HH:mm:ss
			ps2.setString(3, m.getMensaje());
			ps2.setLong(4, m.getIdEjemplar());
			ps2.setLong(5, idpersona);
			ins =ps2.executeUpdate();
			ps2.close();

			String sqlIns3 = "INSERT INTO seguimientos(idpersona, idejemplar, idmensaje) VALUES(?, ?, ?)";
			PreparedStatement ps3 = con.prepareStatement(sqlIns3);
			ps3.setLong(1, m.getIdPersona());
			ps3.setLong(2, m.getIdEjemplar());
			ps3.setLong(3, m.getId());
			ps3.executeUpdate();
			ps3.close();

		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException en `nuevoMensaje`: " + e.getMessage());
			e.printStackTrace();
		}
		return ins;
	}

	
	
	public List<Mensaje> filtrarMensajePersona(Long idPersona) {
		List<Mensaje> mensajes = new ArrayList<>();
		String mens ="";
		Long idEjemplar;
		LocalDateTime fechaHora;
		long id;
		try {
			if (this.con == null || this.con.isClosed())
				this.con = ConexionBD.getInstance().getConnection();

			String sql1 = "Select * FROM mensajes where idpersona=?";
			ps = con.prepareStatement(sql1);
			ps.setLong(1, idPersona);
            rs = ps.executeQuery();
         
           
            
            while (rs.next()) {
            	id=rs.getLong("id");
                 mens=rs.getString("mensaje");
                 idEjemplar=rs.getLong("idejemplar");
                 
                 Timestamp sqlFechaHora = rs.getTimestamp("fechahora");
                 fechaHora = sqlFechaHora.toLocalDateTime();
                 	 Mensaje mensaje = new Mensaje(id, fechaHora, mens,idEjemplar,idPersona);
	            
					mensajes.add(mensaje);
               
            } 
             
            ConexionBD.cerrarConexion();
            ps.close();
            rs.close();
			

			

		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException en `nuevoMensaje`: " + e.getMessage());
			e.printStackTrace();
		}
		return mensajes;
	}
	
	public List<Mensaje> filtrarMensajeTipoPlanta(String codigo) {
		List<Mensaje> mensajes = new ArrayList<>();
		String mens ="";
		Long idEjemplar,idPersona;
		LocalDateTime fechaHora;
		long id;
		try {
			if (this.con == null || this.con.isClosed())
				this.con = ConexionBD.getInstance().getConnection();

			String sql1 = "SELECT * FROM mensajes WHERE idejemplar IN (SELECT id FROM ejemplares WHERE codigoplanta = ?)";

			 ps = con.prepareStatement(sql1);
             ps.setString(1, codigo);
             rs = ps.executeQuery();
            
            while (rs.next()) {
            	id=rs.getLong("id");
                 mens=rs.getString("mensaje");
                 idEjemplar=rs.getLong("idejemplar");
                 idPersona=rs.getLong("idpersona");
                 Timestamp sqlFechaHora = rs.getTimestamp("fechahora");
                 fechaHora = sqlFechaHora.toLocalDateTime();
                 	 Mensaje mensaje = new Mensaje(id, fechaHora, mens,idEjemplar,idPersona);
	            
					mensajes.add(mensaje);
               
            } 
             
            ConexionBD.cerrarConexion();
            ps.close();
            rs.close();
			

			

		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException en `nuevoMensaje`: " + e.getMessage());
			e.printStackTrace();
		}
		return mensajes;
	}
	public List<Mensaje> filtrarMensajeRangoFechas(LocalDate fecha1,LocalDate fecha2) {
		List<Mensaje> mensajes = new ArrayList<>();
		String mens;
		Long idEjemplar,idPersona;
		LocalDateTime fechaHora;
		long id;
		try {
			if (this.con == null || this.con.isClosed())
				this.con = ConexionBD.getInstance().getConnection();

			String sql1 = "SELECT * FROM mensajes WHERE fechahora  between ? and ? ";
			   ps = con.prepareStatement(sql1);
			LocalDateTime startDateTime = fecha1.atStartOfDay();
            LocalDateTime endDateTime = fecha2.atTime(23, 59, 59);
            ps.setTimestamp(1, Timestamp.valueOf(startDateTime));
            ps.setTimestamp(2, Timestamp.valueOf(endDateTime));
             rs = ps.executeQuery();
            
            while (rs.next()) {
            	id=rs.getLong("id");
                 mens=rs.getString("mensaje");
                 idEjemplar=rs.getLong("idejemplar");
                 idPersona=rs.getLong("idpersona");
                 Timestamp sqlFechaHora = rs.getTimestamp("fechahora");
                 fechaHora = sqlFechaHora.toLocalDateTime();
                 	 Mensaje mensaje = new Mensaje(id, fechaHora, mens,idEjemplar,idPersona);
	            
					mensajes.add(mensaje);
               
            } 
             
            ConexionBD.cerrarConexion();
            ps.close();
            rs.close();
			

			

		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException en `nuevoMensaje`: " + e.getMessage());
			e.printStackTrace();
		}
		return mensajes;
	}

	public List<Mensaje> filtrarMensajeEjemplar(long idEjem) {
		List<Mensaje> mensajes = new ArrayList<>();
		
		
		
		
		try {
			if (this.con == null || this.con.isClosed())
				this.con = ConexionBD.getInstance().getConnection();

			String sql1 = "Select * FROM mensajes where idejemplar=? order by fechahora desc;";
			ps = con.prepareStatement(sql1);
			ps.setLong(1, idEjem);
            rs = ps.executeQuery();
         
           
            
            while (rs.next()) {
            	long id=rs.getLong("id");
                 String mens=rs.getString("mensaje");
               long  idEjemplar=rs.getLong("idejemplar");
                 long idPersona = rs.getLong("idpersona");
                 Timestamp sqlFechaHora = rs.getTimestamp("fechahora");
                 LocalDateTime fechaHora = sqlFechaHora.toLocalDateTime();
                 	 Mensaje mensaje = new Mensaje(id, fechaHora, mens,idEjemplar,idPersona);
	            
					mensajes.add(mensaje);
               
            } 
             
            ConexionBD.cerrarConexion();
            ps.close();
            rs.close();
			

			

		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException: " + e.getMessage());
			e.printStackTrace();
		}
		return mensajes;
}
	
	public long generarIdMensaje() {
		long id = 0;
	
		 try {
				if( this.con ==null ||this.con.isClosed()) 
					   this.con=ConexionBD.getInstance().getConnection();
	    
	    String sql = "SELECT COALESCE(MAX(id), 0) + 1 AS nuevo_id FROM mensajes";
	    
	    PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery();
	        
	        
	        if (rs.next()) {
	            id = rs.getLong("nuevo_id");
	        }
	        ps.close();
	        rs.close();
	        
	    } catch (SQLException e) {
	        System.err.println("Error al generar un nuevo ID: " + e.getMessage());
	        e.printStackTrace();
	  
	    }
	    return id;
	}
}

