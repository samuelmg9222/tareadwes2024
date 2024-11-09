package vista;

import java.util.Scanner;
import controlador.Controlador;
import modelo.Credenciales;

public class VistaInicial {

    VistaPlantas portalVPlantas = VistaPlantas.getPortalvistaPlantas();
    VistaEjemplares portalVEjemplares = VistaEjemplares.getPortalvistaEjemplares();
    VistaPersona portalVPersonas = VistaPersona.getPortalvistaPersona();
    private Scanner in = new Scanner(System.in);

    private String username;
    private String password;
    private Long idpersona;

    public void mostrarMenuPrincipal() {
        int opcionint;
        do {
            System.out.println("\n\tSistema Gestor del Vivero (Modo invitado)\n");
            System.out.println("\t1 - Visualizar plantas\n");
            System.out.println("\t2 - Iniciar sesión\n");
            System.out.println("\t99 - Salir de la aplicación\n");
            System.out.print("\tSelecciona una opción: ");
            opcionint = obtenerOpcionValida();

            switch (opcionint) {
                case 1:
                    mostrarListadoPlantas();
                    break;
                case 2:
                    iniciarSesion();
                    break;
                case 99:
                    System.out.println("Saliendo...");
                    System.exit(0);
                    break;
                default:
                    opcNoValida();
                    break;
            }
        } while (opcionint != 99);
    }

    private void mostrarListadoPlantas() {
        System.out.println("\nListado de Plantas");
        portalVPlantas.listarPlantas();
    }

    private void iniciarSesion() {
        System.out.print("Nombre de usuario: ");
        username = in.nextLine().toUpperCase().trim(); // Cambiado a nextLine()
        System.out.print("Contraseña: ");
        password = in.nextLine().trim(); // Cambiado a nextLine()

        int tipouser = Controlador.getServicios().getServiciosCredenciales().registro(username, password);
        Credenciales cr = Controlador.getServicios().getServiciosCredenciales().findBy(username);
        idpersona = cr.getIdpersona();

        if (tipouser == -1) {
            System.out.println("Contraseña o usuario incorrectos");
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
        int opcionint;
        boolean activo = true;
        while (activo) {
            System.out.println("\n\t\tMenú del Personal");
            System.out.println("\t\tUsuario actual: " + username + "\n");
            System.out.println("\t\t1 - Gestión de ejemplares\n");
            System.out.println("\t\t2 - Ver plantas\n");
            System.out.println("\t\t99 - Cerrar sesión\n");
            System.out.print("\tSelecciona una opción: ");
            opcionint = obtenerOpcionValida();

            if (opcionint == 99) {
                System.out.println("Saliendo...");
                activo = false;
                break;
            }
            switch (opcionint) {
                case 1:
                    portalVEjemplares.mostrarMenuGestionEjemplares(idpersona);
                    break;
                case 2:
                    portalVPlantas.listarPlantas();
                    break;
                default:
                    opcNoValida();
                    break;
            }
        }
    }

    private void mostrarMenuAdmin() {
        int opcionIntAdmin;
        do {
            System.out.println("\n\tMenú del Administrador\n");
            System.out.println("\t1 - Gestión de plantas\n");
            System.out.println("\t2 - Gestión de ejemplares\n");
            System.out.println("\t3 - Gestión de empleados\n");
            System.out.println("\t99 - Cerrar sesión\n");
            System.out.print("\tSelecciona una opción: ");
            opcionIntAdmin = obtenerOpcionValida();

            if (opcionIntAdmin == 99) {
                System.out.println("Sesión cerrada. Volviendo al menú invitado...");
                break;
            }

            switch (opcionIntAdmin) {
                case 1:
                    portalVPlantas.mostrarMenuGestionPlantas();
                    break;
                case 2:
                    portalVEjemplares.mostrarMenuGestionEjemplares(idpersona);
                    break;
                case 3:
                    portalVPersonas.mostrarMenuGestionPersonas(idpersona);
                    break;
                default:
                    opcNoValida();
                    break;
            }
        } while (opcionIntAdmin != 99);
    }

    //Método para obtener y validar que la opción ingresada sea un entero válido
    private int obtenerOpcionValida() {
        while (true) {
            if (in.hasNextInt()) {
                int opcion = in.nextInt();
                in.nextLine();
                return opcion;
            } else {
                System.out.println("Entrada no válida. Por favor, introduzca solo un número sin espacios.");
                in.nextLine();
            }
        }
    }
}
