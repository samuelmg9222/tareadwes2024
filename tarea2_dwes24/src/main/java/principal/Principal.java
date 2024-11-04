package principal;



import java.util.List;
import java.util.Scanner;

import Fachada.Fachada;
import dao.PlantaDAO;
import dao.EjemplarDAO;
import modelo.Planta;

public class Principal {
    
public static void main(String[] args) {
		
		Fachada portal = Fachada.getPortal();


		Scanner in = new Scanner(System.in);
		System.out.println("Programa de gestion de un invernadero");

		int opcion = 0;
		do {
			portal.mostrarMenuPrincipal();
			opcion = in.nextInt();
			if (opcion < 1 || opcion > 3) {
				System.out.println("Opcion incorrecta.");
				continue;
			}
			switch (opcion) {
			case 1:
				portal.mostrarMenuGestionPlantas();
				break;
			case 2:
				portal.mostrarMenuGestionEjemplares();
				break;
			case 3:
				portal.mostrarMenuGestionMensajes();
				break;
			
				
			}
		} while (opcion != 99);
		System.out.println("Fin del programa");
		
		
		
		
		System.out.println("Programa de gestion de un invernadero");
String codigo = null,nombrecomun = null,nombrecientifico = null;
		
		Planta nueva= new Planta (codigo,nombrecomun,nombrecientifico);
		
		Controlador.getServicios().getServiciosPlanta();
	}

}
    
