package vista;

import java.sql.Connection;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import utilidades.ConexionBD;

public class VistaPersona {
	private Connection con;
	private static VistaPersona portalvistaPersona;

	public VistaPersona(Connection con) {
		this.con = con;

	}

	public static VistaPersona getPortalvistaPersona() {
		if (portalvistaPersona == null)
			portalvistaPersona = new VistaPersona(ConexionBD.getInstance().getConnection());
		return portalvistaPersona;
	}

	

	public void mostrarMenuGestionPersonas(Long idpersona) {

		Scanner in = new Scanner(System.in);
		System.out.println("Gestion de ejemplares");
		String opcion = "";
		int opcionInt = -1;
		do {
			System.out.println("\n\tSeleccione una opción:\n");
			System.out.println("\t1.  Registrar persona para dar de alta un nuevo usuario de perfil Personal\n");

	
			opcion = in.next().trim();
			if (!opcion.matches("\\d+")) { 
	            System.out.println("Entrada no válida. Por favor, introduzca solo un número sin espacios.");
	            continue;  
	        }
			try {

				opcionInt = Integer.parseInt(opcion.trim());

			} catch (NumberFormatException e) {

				opcionInt = -1;
			}

			if (opcionInt == 99) {
				System.out.println("Volviendo...");

			} else if (opcionInt < 1 || opcionInt > 1) {
				System.out.println("Opción incorrecta.");
				continue;
			}
			switch (opcionInt) {
			case 1:
				
				System.out.println("Dame el nombre de la persona que quieres registrar");
				String nombre=in.nextLine().trim().toUpperCase();
				
				System.out.println("Dame el email de la persona que quieres registrar");
				String email=in.nextLine().trim().toUpperCase();
				
				System.out.println("Dame el nombre de usuario de la persona que quieres registrar");
				String usuario=in.nextLine().trim().toUpperCase();
				
				
				System.out.println("Dame la contraseña de la persona que quieres registrar");
				String contraseña=in.nextLine().trim().toUpperCase();
				
				break;
				
				default:
					System.out.println("Opción no valida.");	
			break;
			
			}	
}while(opcionInt==9999);
		}
		
	}

