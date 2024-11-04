package principal;



import java.util.List;
import java.util.Scanner;

import Fachada.Fachada;
import dao.PlantaDAO;
import dao.EjemplarDAO;
import modelo.Planta;

public class Principal {
	private Fachada fachada;

	public Principal(Fachada fachada) {
        this.fachada = fachada;
    }
public static void main(String[] args) {
		
		Fachada portal = Fachada.getPortal();


		Scanner in = new Scanner(System.in);
		System.out.println("Programa de gestion de un invernadero");
		

		
		    private String username;
		    private String password;
		    private boolean on = true;
		    private final Controlador controlador;

		    

		    public void mostrarMenuPrincipal() {
		        while (on) {
		            System.out.println("\n======= Sistema Gestor del Vivero =======");
		            System.out.println("1 - Visualizar plantas");
		            System.out.println("2 - Iniciar sesión de usuario");
		            System.out.println("9 - Salir de la aplicación");
		            System.out.print("Selecciona una opción: ");

		            int opcion = obtenerOpcionUsuario();
		            switch (opcion) {
		                case 1:
		                    mostrarListadoPlantas();
		                    break;
		                case 2:
		                    iniciarSesion();
		                    break;
		                case 9:
		                    cerrarAplicacion();
		                    break;
		                default:
		                    mostrarMensajeOpcionInvalida();
		                    break;
		            }
		        }
		    }

		    private int obtenerOpcionUsuario() {
		        try {
		            return sc.nextInt();
		        } catch (InputMismatchException e) {
		            sc.next(); // Limpiar entrada
		            System.err.println("Opción inválida. Por favor, introduce un número.");
		            return -1;
		        }
		    }

		    private void mostrarListadoPlantas() {
		        System.out.println("\n---- Listado de Plantas ----");
		        controlador.getPlantasMenu().listadoPlantas();
		    }

		    private void iniciarSesion() {
		        System.out.print("Nombre de usuario: ");
		        username = sc.next();
		        System.out.print("Contraseña: ");
		        password = sc.next();

		        int nivel = controlador.getServicioCredenciales().login(username, password);
		        if (nivel == -1) {
		            System.out.println("Error en el usuario o la contraseña.");
		        } else if (nivel == 0) {
		            mostrarMenuUsuarioPersonal();
		        } else if (nivel == 1) {
		            mostrarMenuAdmin();
		        }
		    }

		    private void cerrarAplicacion() {
		        System.out.println("Saliendo de la aplicación. ¡Hasta luego!");
		        on = false;
		    }

		    private void mostrarMensajeOpcionInvalida() {
		        System.out.println("Opción no válida. Selecciona una opción de las presentadas.");
		    }

		    // Menú para usuarios con permisos de personal
		    private void mostrarMenuUsuarioPersonal() {
		        boolean activo = true;
		        while (activo) {
		            System.out.println("\n---- Menú del Personal ----");
		            System.out.println("Usuario actual: " + username);
		            System.out.println("1 - Gestión de ejemplares");
		            System.out.println("9 - Cerrar sesión");

		            int opcion = obtenerOpcionUsuario();
		            switch (opcion) {
		                case 1:
		                    controlador.getEjemplaresMenu().menu();
		                    break;
		                case 9:
		                    System.out.println("Sesión cerrada. Volviendo al menú principal...");
		                    activo = false;
		                    break;
		                default:
		                    mostrarMensajeOpcionInvalida();
		                    break;
		            }
		        }
		    }

		    // Menú para administradores
		    private void mostrarMenuAdmin() {
		        boolean activo = true;
		        while (activo) {
		            System.out.println("\n---- Menú del Administrador ----");
		            System.out.println("Usuario actual: " + username);
		            System.out.println("1 - Gestión de plantas");
		            System.out.println("2 - Gestión de ejemplares");
		            System.out.println("3 - Gestión de empleados");
		            System.out.println("9 - Cerrar sesión");

		            int opcion = obtenerOpcionUsuario();
		            switch (opcion) {
		                case 1:
		                    Fachada.getPlantasMenu().menuPlantas();
		                    break;
		                case 2:
		                    controlador.getEjemplaresMenu().menu();
		                    break;
		                case 3:
		                    controlador.getPersonaMenu().menuPersona();
		                    break;
		                case 9:
		                    System.out.println("Sesión cerrada. Volviendo al menú principal...");
		                    activo = false;
		                    break;
		                default:
		                    mostrarMensajeOpcionInvalida();
		                    break;
		            }
		        }
		    }
		}

	}

}
    
