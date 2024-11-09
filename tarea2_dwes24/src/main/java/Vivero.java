import java.sql.Connection;


import utilidades.ConexionBD;
import vista.VistaInicial;


public class Vivero {

	public static void main(String[] args) {
		ConexionBD c=new ConexionBD();
		Connection conexion = c.getConnection();

	        if (conexion == null) {

	            System.exit(1);
	        }
	        VistaInicial menu=new VistaInicial();
		menu.mostrarMenuPrincipal();


	} 

}