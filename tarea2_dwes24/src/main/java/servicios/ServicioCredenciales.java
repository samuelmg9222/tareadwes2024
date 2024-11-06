package servicios;

import dao.CredencialesDAO;
import dao.MensajeDAO;
import modelo.Credenciales;
import principal.ConexionBD;

public class ServicioCredenciales {
	private ConexionBD con;
	private CredencialesDAO credencialesDAO;
public ServicioCredenciales() {
	con=ConexionBD.getInstance();
	credencialesDAO=(CredencialesDAO) con.getCredencialesDAO();

}
	public Credenciales findBy(String user) {
		 Credenciales credenciales=credencialesDAO.findByUsuario(user);
		 return credenciales;
	}
	
	
	
	   public int registro(String usuario, String contraseña){
	        Credenciales credenciales = credencialesDAO.findByUsuario(usuario);
	        if (usuario.equals("admin") && contraseña.equals("admin")){
	            return 0;
	        }

	        if (usuario.equals(credenciales.getUsuario()) && contraseña.equals(credenciales.getPassword())){
	            return 1;
	        }
	        return -1;
	    }
	
}
