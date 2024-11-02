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
import principal.ConexionBD;

public class MensajeDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public MensajeDAO(Connection c) {
		this.con = c;
	}

	public void nuevoMensaje(Long idMensaje, LocalDateTime fechaHora, String mensaje, Long idEjemplar, Long idPersona) {
		try {
			if (this.con == null || this.con.isClosed())
				this.con = ConexionBD.getInstance().getConnection();

			String sqlIns2 = "INSERT INTO mensajes(id, fechahora, mensaje, idejemplar, idpersona) VALUES(?, ?, ?, ?, ?)";
			PreparedStatement ps2 = con.prepareStatement(sqlIns2);
			ps2.setLong(1, idMensaje);
			 ps2.setTimestamp(2, Timestamp.valueOf(fechaHora));
			ps2.setString(3, mensaje);
			ps2.setLong(4, idEjemplar);
			ps2.setLong(5, idPersona);
			ps2.executeUpdate();
			ps2.close();

			String sqlIns3 = "INSERT INTO seguimientos(idpersona, idejemplar, idmensaje) VALUES(?, ?, ?)";
			PreparedStatement ps3 = con.prepareStatement(sqlIns3);
			ps3.setLong(1, idPersona);
			ps3.setLong(2, idEjemplar);
			ps3.setLong(3, idMensaje);
			ps3.executeUpdate();
			ps3.close();

		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException en `nuevoMensaje`: " + e.getMessage());
			e.printStackTrace();
		}
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
	
	public List<Mensaje> filtrarMensajeTipoPlanta(Long codigo) {
		List<Mensaje> mensajes = new ArrayList<>();
		String mens ="";
		Long idEjemplar,idPersona;
		LocalDateTime fechaHora;
		long id;
		try {
			if (this.con == null || this.con.isClosed())
				this.con = ConexionBD.getInstance().getConnection();

			String sql1 = "SELECT * FROM mensajes WHERE idejemplar = (SELECT id FROM ejemplares WHERE codigoplanta = ?)";

			 ps = con.prepareStatement(sql1);
             ps.setLong(1, codigo);
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

	public List<Mensaje> filtrarMensajeEjemplar(String idEjem) {
		List<Mensaje> mensajes = new ArrayList<>();
		
		
		
		
		try {
			if (this.con == null || this.con.isClosed())
				this.con = ConexionBD.getInstance().getConnection();

			String sql1 = "Select * FROM mensajes where idejemplar=? order by fechahora desc;";//Asi se añaden primero los mas recientes
			ps = con.prepareStatement(sql1);
			ps.setString(1, idEjem);
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
}
