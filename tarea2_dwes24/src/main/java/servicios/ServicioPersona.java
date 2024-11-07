package servicios;

import java.util.List;

import dao.MensajeDAO;
import dao.PersonaDAO;
import modelo.Persona;
import utilidades.ConexionBD;

public class ServicioPersona {
	private ConexionBD con;
	private PersonaDAO personaDAO;
public ServicioPersona() {
	con=ConexionBD.getInstance();
	 personaDAO = (PersonaDAO) con.getPersonaDAO();

}


public  boolean existeUsuario(String cod) {
	return personaDAO.existeUsuario(cod);
	
}
public  String obtenerNombreUsuario(Long cod) {
	return personaDAO.obtenerNombreUsuario(cod);
	
}
public Persona obtenerDatosPersona(Long id) {
	return personaDAO.obtenerDatosPersona(id);
}
public List<Persona> Find() {
	return personaDAO.findAll();
}
}
