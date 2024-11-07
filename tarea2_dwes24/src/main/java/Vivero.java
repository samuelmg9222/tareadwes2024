import Fachada.Fachada;
import utilidades.ConexionBD;
import vista.MenuPrincipal;


public class Vivero {

	public static void main(String[] args) {
		
		  ConexionBD conexion = ConexionBD.getInstance();

	        if (conexion == null) {
	            System.out.println("Error crítico: no se pudo conectar a la base de datos. Terminando el programa.");
	            System.exit(1);
	        }
		MenuPrincipal menu=new MenuPrincipal();
		menu.mostrarMenuPrincipal();


	}

}
