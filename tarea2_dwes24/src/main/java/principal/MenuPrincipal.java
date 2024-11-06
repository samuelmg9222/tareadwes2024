package principal;



import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Fachada.Fachada;
import dao.PlantaDAO;
import dao.EjemplarDAO;
import modelo.Credenciales;
import modelo.Planta;
import vista.VistaEjemplares;
import vista.VistaPlantas;

public class MenuPrincipal{

	

	
		VistaPlantas portalVPlantas= VistaPlantas.getPortalvistaPlantas();
		VistaEjemplares portalVEjemplares= VistaEjemplares.getPortalvistaEjemplares();

		private Scanner in = new Scanner(System.in);

		

		
		    private String username;
		    private String password;
		    private boolean activo = true;
		    private Long idpersona;

		    

		    public void mostrarMenuPrincipal() {
		    	int opcionint=-1;
		        while (activo) {
		            System.out.println("\nSistema Gestor del Vivero (Modo invitado)\n");
		            System.out.println("1 - Visualizar plantas\n");
		            System.out.println("2 - Iniciar sesión\n");
		            System.out.println("99 - Salir de la aplicación\n");
		            System.out.println("Selecciona una opción: ");
		            String opcion=in.next().trim();
		            try {

						opcionint = Integer.parseInt(opcion.trim());

					} catch (NumberFormatException e) {

						opcionint = -1;
					}
					if (opcionint == 99) {
						System.out.println("Saliendo...");
						activo=false;
						System.exit(0);
					}
		            
		            switch (opcionint) {
		                case 1 :
		                    mostrarListadoPlantas();
		                    break;
		                case 2:
		                	 System.out.println("Intentando iniciar sesión...");
		                    iniciarSesion();
		                    break;
		                
		                  
		                default:
		                    opcNoValida();
		                    break;
		            }
		        }
		    }


		    private void mostrarListadoPlantas() {
		        System.out.println("\nListado de Plantas");
		        portalVPlantas.listarPlantas();
		    }

		    private void iniciarSesion() {
		        System.out.println("Nombre de usuario: ");
				username = in.next().toLowerCase().trim();
		        System.out.println("Contraseña: ");
		        password = in.next();
		        int tipouser= Controlador.getServicios().getServiciosCredenciales().registro(username, password);
		        	Credenciales cr=Controlador.getServicios().getServiciosCredenciales().findBy(username);
		        	 idpersona = cr.getIdpersona();
		     
		        if (tipouser == -1) {
		            System.out.println("Contrasña o usuario incorrectos");
		        } else if (tipouser == 1) {
		            mostrarMenuUsuarioPersonal();
		        } else if (tipouser == 0) {
		            mostrarMenuAdmin();
		        }
		    }

		    private void opcNoValida() {
		        System.out.println("Lamentablemente esa opción no es válida. Selecciona una opción de las presentadas.");
		    }

		    private void mostrarMenuUsuarioPersonal() {
		        boolean activo = true;
		        while (activo) {
		            System.out.println("\nMenú del Personal");
		            System.out.println("Usuario actual: " + username+"\n");
		            System.out.println("1 - Gestión de ejemplares\n");
		            System.out.println("99 - Cerrar sesión\n");

		            int opcion = 0 ;
		            switch (opcion) {
		                case 1:
		                	VistaPlantas.getPortalvistaPlantas().listarPlantas();
		                    break;
		                case 99:
		                    System.out.println("Sesión cerrada. Volviendo al menú principal...");
		                    activo = false;
		                    break;
		                default:
		                    opcNoValida();
		                    break;
		            }
		        }
		    }

		    
		    
		    private void mostrarMenuAdmin() {
		    int	opcionIntAdmin=-1;
		        boolean activo = true;
		        while (activo) {
		            System.out.println("\nMenú del Administrador\n");
		            System.out.println("1 - Gestión de plantas\n");
		            System.out.println("2 - Gestión de ejemplares\n");
		            System.out.println("3 - Gestión de empleados\n");
		            System.out.println("99 - Cerrar sesión\n");
		            String opcion=in.next().trim();
		            try {

		            	opcionIntAdmin = Integer.parseInt(opcion.trim());

					} catch (NumberFormatException e) {

						opcionIntAdmin = -1;
					}
					if (opcionIntAdmin == 99) {
						System.out.println("Saliendo...");
						activo=false;
						break;
					}
		            
		           
		            switch (opcionIntAdmin) {
		                case 1:
		                    portalVPlantas.mostrarMenuGestionPlantas();
		                    break;
		                case 2:
		                   portalVEjemplares.mostrarMenuGestionEjemplares(idpersona);
		                    break;
		                
		               default:
		                    opcNoValida();
		                    break;
		            }
		        }
		    }
		

	}


    