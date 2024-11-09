package servicios;

import java.util.List;

import controlador.Controlador;
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
public  boolean existeEmail(String cod) {
	return personaDAO.existeEmail(cod);
	
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

public int verificarPersona(String nombre,String email,String usuario,String contraseña) {
	if(!nombre.matches("^[A-Za-z]+([ -][A-Za-zÀ-ÿ]+)*$")) {
		return -1;
	}
	if(!email.matches("^[\\w.%+-]+@[\\w.-]+\\.[A-Za-z]{2,6}$")) {
		return -2;
	}
	if(!usuario.matches("^[A-Za-z0-9]{3,}$")) {
		return -3;
	}
	if(!contraseña.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
		return -4;
	}
	 if(Controlador.getServicios().getServiciosCredenciales().findBy(usuario).getId()!=-1) 
         	{
         	  
         	  return -5;
          }
	 if(Controlador.getServicios().getServiciosPersona().existeEmail(email) 
	         	) {
	         	 
	         	  return -6;
	          }
	
	return 1;
}


public boolean insertarPersona(Long id, String nombre, String email) {
	return personaDAO.nuevoUsuarioPersonal(id, nombre, email);
}

public long generarIdPersona(){
	return personaDAO.generarIdPersona();
}
}
