package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import principal.ConexionBD;

public class  MensajeDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public MensajeDAO(Connection c) {
		this.con = c;
	}
	
	

    public void nuevoMensaje(Long idMensaje, LocalDate fechaHora, String mensaje, 
                             Long idEjemplar, Long idPersona) {
    	try {
			if (this.con == null || this.con.isClosed())
				this.con = ConexionBD.getInstance().getConnection();
           
            String sqlIns2 = "INSERT INTO mensajes(id, fechahora, mensaje, idejemplar, idpersona) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement ps2 = con.prepareStatement(sqlIns2);
            ps2.setLong(1, idMensaje);
            ps2.setDate(2, Date.valueOf(fechaHora));
            ps2.setString(3, mensaje);
            ps2.setLong(4, idEjemplar);
            ps2.setLong(5, idPersona);
            ps2.executeUpdate();
            ps2.close();
            System.out.println("Inserción en la tabla `mensajes` completada con éxito.");

         
            String sqlIns3 = "INSERT INTO seguimientos(idpersona, idejemplar, idmensaje) VALUES(?, ?, ?)";
            PreparedStatement ps3 = con.prepareStatement(sqlIns3);
            ps3.setLong(1, idPersona);
            ps3.setLong(2, idEjemplar);
            ps3.setLong(3, idMensaje);
            ps3.executeUpdate();
            ps3.close();
            
            System.out.println("Inserción en la tabla `seguimientos` completada con éxito.");
            
        } catch (SQLException e) {
            System.out.println("Se ha producido una SQLException en `nuevoMensaje`: " + e.getMessage());
            e.printStackTrace();
        }
    }
	
	
	
	
	
	
}
