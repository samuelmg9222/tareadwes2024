package principal;



import java.util.List;
import java.util.Scanner;

import Fachada.Fachada;
import dao.PlantaDAO;
import dao.EjemplarDAO;
import modelo.Planta;

public class Vista {
    private Scanner in = new Scanner(System.in);
    private Fachada fachada;

    public Vista(Fachada fachada) {
        this.fachada = fachada;
    }

    public void mostrarMenuPrincipal() {
        System.out.println("\n\tSeleccione una opción:\n");
        System.out.println("\t1. Gestionar Plantas.\n");
        System.out.println("\t2. Gestionar Ejemplares.\n");
        System.out.println("\t3. Gestionar Mensajes.\n");
        System.out.println("\t99. Salir\n");
    }

    public void iniciar() {
        int opcionInt = -1;
        do {
            mostrarMenuPrincipal();
            opcionInt = leerOpcion();

            switch (opcionInt) {
                case 1 -> gestionarPlantas();
                case 2 -> gestionarEjemplares();
                case 3 -> gestionarMensajes();
                case 99 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción incorrecta.");
            }
        } while (opcionInt != 99);
    }

    private int leerOpcion() {
        String opcion = in.next().trim();
        try {
            return Integer.parseInt(opcion);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void gestionarPlantas() {
        int opcionInt;
        do {
            System.out.println("\n\tGestión de Plantas\n");
            System.out.println("\t1. Crear nueva Planta (CU4_A).\n");
            System.out.println("\t2. Ver plantas (CU1).\n");
            System.out.println("\t3. Modificar planta (CU4_B).\n");
            System.out.println("\t99. Volver al menú Principal\n");
            opcionInt = leerOpcion();

            switch (opcionInt) {
                case 1 -> fachada.crearPlanta();
                case 2 -> fachada.verPlantas();
                case 3 -> fachada.modificarPlanta();
                case 99 -> System.out.println("Volviendo al menú Principal...");
                default -> System.out.println("Opción incorrecta.");
            }
        } while (opcionInt != 99);
    }

    private void gestionarEjemplares() {
        int opcionInt;
        do {
            System.out.println("\n\tGestión de Ejemplares\n");
            System.out.println("\t1. Registrar ejemplar (CU5_A).\n");
            System.out.println("\t2. Filtrar ejemplares por tipo de planta (CU5_B).\n");
            System.out.println("\t3. Ver mensajes para ejemplar (CU5_C).\n");
            System.out.println("\t99. Volver al menú Principal\n");
            opcionInt = leerOpcion();

            switch (opcionInt) {
                case 1 -> fachada.registrarEjemplar();
                case 2 -> fachada.filtrarEjemplares();
                case 3 -> fachada.verMensajesEjemplar();
                case 99 -> System.out.println("Volviendo al menú Principal...");
                default -> System.out.println("Opción incorrecta.");
            }
        } while (opcionInt != 99);
    }

    private void gestionarMensajes() {
        int opcionInt;
        do {
            System.out.println("\n\tGestión de Mensajes\n");
            System.out.println("\t1. Crear Mensaje (CU6).\n");
            System.out.println("\t2. Filtrar mensaje (CU6).\n");
            System.out.println("\t99. Volver al menú Principal\n");
            opcionInt = leerOpcion();

            switch (opcionInt) {
                case 1 -> fachada.crearMensaje();
                case 2 -> fachada.filtrarMensaje();
                case 99 -> System.out.println("Volviendo al menú Principal...");
                default -> System.out.println("Opción incorrecta.");
            }
        } while (opcionInt != 99);
    }
}