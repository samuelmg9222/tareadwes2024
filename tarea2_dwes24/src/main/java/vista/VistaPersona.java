package vista;

import java.sql.Connection;
import java.util.Scanner;

import controlador.Controlador;
import modelo.Credenciales;
import utilidades.ConexionBD;

public class VistaPersona {
    private Connection con;
    private static VistaPersona portalvistaPersona;

    public VistaPersona(Connection con) {
        this.con = con;
    }

    public static VistaPersona getPortalvistaPersona() {
        if (portalvistaPersona == null) {
            portalvistaPersona = new VistaPersona(ConexionBD.getInstance().getConnection());
        }
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
            System.out.println("\t99. Volver\n");

            opcion = in.nextLine().trim();
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
                break;
            } else if (opcionInt < 1 || opcionInt > 1) {
                System.out.println("Opción incorrecta.");
                continue;
            }

            switch (opcionInt) {
          
                case 1:
                    String contraseña = "", nombre = "", email = "", usuario = "";
                 
                    do {
                        System.out.println("Dame el nombre de la persona que quieres registrar (9999 para cancelar el proceso)");
                        nombre = in.nextLine().trim().toUpperCase();
                        if (nombre.equals("9999")) break;

                        System.out.println("Dame el email de la persona que quieres registrar (9999 para cancelar el proceso)");
                        email = in.nextLine().trim().toUpperCase();
                        if (email.equals("9999")) break;

                        System.out.println("Dame el nombre de usuario de la persona que quieres registrar (9999 para cancelar el proceso)");
                        usuario = in.nextLine().trim().toUpperCase();
                        if (usuario.equals("9999")) break;

                        System.out.println("Dame la contraseña de la persona que quieres registrar (9999 para cancelar el proceso)");
                        contraseña = in.nextLine().trim();
                        if (contraseña.equals("9999")) break;

                        switch (Controlador.getServicios().getServiciosPersona().verificarPersona(nombre, email, usuario, contraseña)) {
                       
                        case -1:
                            mostrarMensajeErrorNombrePersona();
                        break;
                        
                        case -2:
                            mostrarMensajeErrorEmail();
                        break;
                        
                        case -3:
                            mostrarMensajeErrorNombreUsuario();
                            break;
                        case -4:
                            mostrarMensajeErrorContrasena();
                            break;
                        
                        case -5:   
                            System.out.println("Ese nombre de usuario ya existe, intentalo de nuevo con otro");
                            break;
                            
                        case -6:   
                            System.out.println("Ese email ya existe,intentalo de nuevo con otro");
                            break;
                        }
                        
                    } while (Controlador.getServicios().getServiciosPersona().verificarPersona(nombre, email, usuario, contraseña) != 1);
                    
                    if (contraseña.equals("9999") || usuario.equals("9999") || email.equals("9999") || nombre.equals("9999")) {
                        break;
                    }
                    
                    Long id = 0l;
                    Long idCr = 0l;
                    
                    if (Controlador.getServicios().getServiciosPersona().generarIdPersona() != 0) {
                        id = Controlador.getServicios().getServiciosPersona().generarIdPersona();
                        if (Controlador.getServicios().getServiciosPersona().insertarPersona(id, nombre, email)) {

                            System.out.println("Nueva persona registrada con exito: Nombre: " + nombre + " Email: " + email + " id: " + id);

                            if (Controlador.getServicios().getServiciosCredenciales().generaridcredenciales() != 0) {
                                idCr = Controlador.getServicios().getServiciosCredenciales().generaridcredenciales();
                                Credenciales c = new Credenciales(idCr, usuario, contraseña, id);
                                if (Controlador.getServicios().getServiciosCredenciales().insertarcredenciales(c)) {
                                    System.out.println("Se ha completado el registro al completo con exito, el nombre de usuario elegido es " + usuario);
                                    
                                } else {
                                    System.out.println("No se ha podido insertar las credenciales para el usuario por un error a la hora de su inserción");
                                }
                            } else {
                                System.out.println("No se ha podido completar el proceso de insertar las credenciales para el usuario por un error al generar el id de la credencial");
                            }
                        } else {
                            System.out.println("No se ha podido completar el proceso debido a un error a la hora de insertar la persona");
                        }
                    } else {
                        System.out.println("No se ha podido completar el proceso por un error al generar el id de la persona");
                    }
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcionInt != 99);
    }
    
    
    public static void mostrarMensajeErrorEmail() {
        System.out.println("Error: Formato de correo electrónico no válido.\n");
        System.out.println("Por favor, asegúrate de que el correo electrónico ingresado cumpla con el siguiente formato:");
        System.out.println("1. Comienza con letras, números o los caracteres especiales permitidos: ., %, +, -");
        System.out.println("2. Sigue con un símbolo '@'");
        System.out.println("3. Incluye un dominio válido, como ejemplo.com, y una extensión entre 2 y 6 letras (por ejemplo, .com, .org, .email)\n");
        
        System.out.println("Ejemplos válidos:");
        System.out.println(" - usuario@example.com");
        System.out.println(" - nombre.apellido@empresa.org");
        System.out.println(" - persona+info@dominio.co\n");

        System.out.println("Ejemplo incorrecto:");
        System.out.println(" - usuario@ejemplo (falta el dominio o la extensión)\n");
        
        System.out.println("Si tienes problemas, revisa el correo electrónico e inténtalo de nuevo.");
    }
    
    public static void mostrarMensajeErrorNombreUsuario() {
        System.out.println("Error: Formato de nombre de usuario no válido.\n");
        System.out.println("Por favor, asegúrate de que el nombre de usuario ingresado cumpla con el siguiente formato:");
        System.out.println("1. Contiene entre 3 y 20 caracteres.");
        System.out.println("2. Solo usa letras, números y guiones bajos (_). No se permiten espacios ni caracteres especiales.\n");

        System.out.println("Ejemplos válidos:");
        System.out.println(" - usuario123");
        System.out.println(" - nombre_apellido");
        System.out.println(" - user2023\n");

        System.out.println("Ejemplo incorrecto:");
        System.out.println(" - usuario* (caracter especial no permitido)");
        System.out.println(" - nombre apellido (no se permiten espacios)\n");

        System.out.println("Si tienes problemas, revisa el nombre de usuario e inténtalo de nuevo.");
    }
    
    public static void mostrarMensajeErrorContrasena() {
        System.out.println("Error: Formato de contraseña no válido.\n");
        System.out.println("Por favor, asegúrate de que la contraseña ingresada cumpla con el siguiente formato:");
        System.out.println("1. Contiene entre 8 y 20 caracteres.");
        System.out.println("2. Incluye al menos una letra mayúscula, una letra minúscula, un número y un caracter especial (como !, @, #, $).\n");

        System.out.println("Ejemplos válidos:");
        System.out.println(" - Password1!");
        System.out.println(" - Segura$2023");
        System.out.println(" - Mi@Contra7\n");
    }
    
    public static void mostrarMensajeErrorNombrePersona() {
        System.out.println("Error: Formato de nombre no válido.\n");
        System.out.println("Por favor, asegúrate de que el nombre ingresado cumpla con el siguiente formato:");
        System.out.println("1. Contiene solo letras sin tildes, sin números ni caracteres especiales.");
        System.out.println("2. Puede incluir espacios o guiones si el nombre es compuesto.");
        System.out.println("3. Tiene una longitud de entre 2 y 50 caracteres.\n");

        System.out.println("Ejemplos válidos:");
        System.out.println(" - Juan Pérez");
        System.out.println(" - María-José");
        System.out.println(" - Ana\n");
    }
}
