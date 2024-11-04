package principal;

import dao.MensajeDAO;
import dao.PersonaDAO;

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
}
