package vista;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import modelo.Planta;
import principal.ConexionBD;
import principal.Controlador;
import utilidades.Utils;

public class VistaPlantas {
	private Connection con;
	private static VistaPlantas portalvistaPlantas;
	public VistaPlantas(Connection con) {
		this.con = con;
		
	}
	public static VistaPlantas getPortalvistaPlantas() {
		if (portalvistaPlantas==null)
			portalvistaPlantas=new VistaPlantas(ConexionBD.getInstance().getConnection());
		return portalvistaPlantas;
	}
	public void mostrarMenuGestionPlantas() {
		Scanner in = new Scanner(System.in);
		System.out.println("Gestion de plantas");
		String opcion = "";
		int opcionInt = -1;
		do {
			System.out.println("\n\tSeleccione una opción:\n");
			System.out.println("\t1.  Crear nueva Planta (CU4_A).\n");
			System.out.println("\t2.  Ver plantas (CU1).\n");
			System.out.println("\t3.  Modificar planta (CU4_B).\n");
			System.out.println("\t99.  Volver al menú Principal\n");
			opcion = in.next().trim();

			try {

				opcionInt = Integer.parseInt(opcion.trim());

			} catch (NumberFormatException e) {

				opcionInt = -1;
			}

			if (opcionInt == 99) {
				System.out.println("Volviendo...");

			} else if (opcionInt < 1 || opcionInt > 3) {
				System.out.println("Opción incorrecta.");
				continue;
			}

			switch (opcionInt) {
			case 1:

				 String nombreComun;
				    String codigo;
				    String nombreCientifico;

				    
				   
				        System.out.println("Dame código de una nueva planta (FORMATO DE 4 LETRAS SIN TILDES [AAAA]):");
				        codigo = in.next().trim();
				    

				    
				        System.out.println("Dame nombre común de la planta:");
				        nombreComun = in.next().trim().toUpperCase();
				    

				    
				        System.out.println("Dame nombre científico de la planta:");
				        nombreCientifico = in.next().trim().toUpperCase();
				   
				        Planta p=new Planta(codigo,nombreComun,nombreCientifico);
				    
				    if (Controlador.getServicios().getServiciosPlanta().verificarPlanta(p)) {
				        try {
				            
				            Controlador.getServicios().getServiciosPlanta().InsertarPlanta(p); 
				            System.out.println(nombreComun + " (" + nombreCientifico + ") ingresada con éxito: código " + codigo + ".");
				        } catch (Exception e) {
				            System.out.println("Se ha producido una excepción: " + e.getMessage());
				        }
				    } else {
				        System.out.println("Los datos de la planta no son válidos. No se puede agregar.");
				    }
				

				break;

			case 2:
				listarPlantas();
				
				break;

			case 3:
		
codigo=""; nombreComun=""; nombreCientifico="";
List<Planta> plantas2=Controlador.getServicios().getServiciosPlanta().findAll();


System.out.println(Utils.obtenerEncabezado());
int i=1;
for (Planta pl : plantas2) {

	System.out.println(i+": "+pl);
	i++;
}
			        try {
			            System.out.println("Dame el numero (indice) de la planta que desea modificar:");
			           String indice = in.next().trim();
int ind =Integer.parseInt(indice);
			        System.out.println("Dame nombre común que desea poner a la planta. Si no desea modificarlo, introduce su nombre como está:");
			        nombreComun = in.next().trim().toUpperCase();

			        System.out.println("Dame nombre científico de la planta. Si no desea modificarlo, introduce su nombre como está:");
			        nombreCientifico = in.next().trim().toUpperCase();
			        Planta pl=new Planta(plantas2.get(ind-1).getCodigo(),nombreComun,nombreCientifico);
				    
			        if (Controlador.getServicios().getServiciosPlanta().verificarModificacion(pl,plantas2,ind)) {
			        	 Controlador.getServicios().getServiciosPlanta().modificarPlanta(pl); 
				            System.out.println("Has modificado la planta de codigo ["+pl.getCodigo()+"] con éxito");
			        	
			        }else {
			        	System.out.println("No se ha podido relaizar la modificación.");
			        }
			        } catch (Exception e) {
			            System.out.println("Se ha producido una excepción: " + e.getMessage());
			        }
			        break;
			
				
			
			case 99:
				break;
			default:
				System.out.println("Opcion no valida.");
				break;
			}
		} while (opcionInt != 99);

	}
	
	
	
	 public List<Planta> listarPlantas(){

		List<Planta> plantas=Controlador.getServicios().getServiciosPlanta().findAll();
		

		System.out.println(Utils.obtenerEncabezado());
		for (Planta pl : plantas) {
			System.out.println(pl);
		}
		return plantas;
}
}

