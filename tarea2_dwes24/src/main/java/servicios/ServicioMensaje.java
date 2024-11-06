package servicios;

import java.time.LocalDate;
import java.util.List;

import dao.EjemplarDAO;
import dao.MensajeDAO;
import modelo.Ejemplar;
import modelo.Mensaje;
import principal.ConexionBD;

public class ServicioMensaje {
	private ConexionBD con;
	private MensajeDAO mensajeDAO;
public ServicioMensaje() {
	con=ConexionBD.getInstance();
	mensajeDAO=(MensajeDAO) con.getMensajeDAO();

}


public int InsertarMensaje(Mensaje m,Long idpersona) {
	return mensajeDAO.nuevoMensaje(m,idpersona);
}
public Long generarIdMensaje() {
	return mensajeDAO.generarIdMensaje();
}
public List<Mensaje> filtrarMensajeEjemplar(String idEjem){
	return mensajeDAO.filtrarMensajeEjemplar(idEjem);
}
public List<Mensaje> filtrarMensajePersona(Long idPer){
	return mensajeDAO.filtrarMensajePersona(idPer);
}
public List<Mensaje> filtrarMensajeTipoPlanta(String codplanta){
	return mensajeDAO.filtrarMensajeTipoPlanta(codplanta);
}
public List<Mensaje> filtrarMensajeRangoFechas(LocalDate inicio,LocalDate fin){
	return mensajeDAO.filtrarMensajeRangoFechas(inicio,fin);
}


public boolean verificarfecha(LocalDate fechaInicio,LocalDate fechaFin,LocalDate fechaActual) {
	if (fechaInicio.isAfter(fechaFin)) {
        System.out.println("La fecha de inicio debe ser anterior o igual a la fecha de fin. Intenta de nuevo.");
        return false;
    }
	
    
    if (fechaInicio.isAfter(fechaActual)) {
        System.out.println("La fecha de inicio no puede ser posterior al día actual. Intenta de nuevo.");
        fechaInicio = null; 
        return false; 
    }
	return true;
}





}