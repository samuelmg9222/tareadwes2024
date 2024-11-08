import java.sql.Connection;

import Fachada.Fachada;
import utilidades.ConexionBD;
import vista.MenuPrincipal;


public class Vivero {

	public static void main(String[] args) {
		ConexionBD c=new ConexionBD();
		Connection conexion = c.getConnection();

	        if (conexion == null) {

	            System.exit(1);
	        }
		MenuPrincipal menu=new MenuPrincipal();
		menu.mostrarMenuPrincipal();


	}

}
