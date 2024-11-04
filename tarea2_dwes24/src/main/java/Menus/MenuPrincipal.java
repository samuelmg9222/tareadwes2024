package Menus;

import java.util.Scanner;

import Fachada.Fachada;

public class MenuPrincipal {
	 public MenuPrincipal() {
	       
	    }
	
	 Fachada portal = Fachada.getPortal();
	
	
	
	 public void menuPrincipal(){
			Scanner in = new Scanner(System.in);
			System.out.println("Gestion de Vivero");
			String opcion = "";
			int opcionInt = -1;
			do {
				   System.out.println("\t  Sistema Gestor del Viviero \n");
		            System.out.println("\t1 - Ver plantas\n");
		            System.out.println("\t2 - Iniciar sesión\n");
		            System.out.println("\t99 - Salir de la aplicación\n");
				opcion = in.next().trim();

				try {

					opcionInt = Integer.parseInt(opcion.trim());

				} catch (NumberFormatException e) {
					System.out.println("La opcion debe de ser un numero entero");
					opcionInt = -1;
				}
				if (opcionInt == 99) {
					System.out.println("Saliendo...");
					System.exit(0);
				} else if (opcionInt < 1 || opcionInt > 2) {
					System.out.println("Opción incorrecta.");
					continue;
				}
				switch (opcionInt) {
				case 1:
					portal.listadoplantas();
					break;
				case 2:
					
					break;
				

				default:
					break;
				}
	 }while(!(opcionInt==99));
			
			
			
	      
}}
