package servicios;

import java.time.LocalDate;
import java.util.List;

import dao.EjemplarDAO;
import dao.MensajeDAO;
import modelo.Ejemplar;
import modelo.Mensaje;
import utilidades.ConexionBD;

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
public List<Mensaje> filtrarMensajeEjemplar(Long idEjem){
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


public int verificarfecha(LocalDate fechaInicio,LocalDate fechaFin,LocalDate fechaActual) {
	if (fechaInicio.isAfter(fechaFin)) {
      
        return -1;
    }
	
    
    if (fechaInicio.isAfter(fechaActual)) {
        
        return -2; 
    }
	return 1;
}





}