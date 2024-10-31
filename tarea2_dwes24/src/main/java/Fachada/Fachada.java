package Fachada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import dao.EjemplarDAO;
import dao.MensajeDAO;
import dao.PlantaDAO;
import modelo.Ejemplar;
import modelo.Planta;
import principal.ConexionBD;
import utilidades.Utils;
public class Fachada {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	public Fachada(Connection c) {
		this.con = c;
		this.plantaDAO = new PlantaDAO(con);
	}
	
	 Utils utils = new Utils(con);
	 
	 EjemplarDAO ejemplarDao = new EjemplarDAO(ConexionBD.getInstance().getConnection());
	    PlantaDAO plantaDAO = new PlantaDAO(ConexionBD.getInstance().getConnection());
	    MensajeDAO mensajeDAO = new MensajeDAO(ConexionBD.getInstance().getConnection());
	 
	 

	public void mostrarMenuPrincipal() {
		System.out.println("Seleccione una opcion:");
		System.out.println("1.  Gestionar Plantas.");
		System.out.println("2.  Gestionar Ejemplares.");
		System.out.println("8.  Salir");
	}


	public void mostrarMenuPrincipalPlantas() {
		System.out.println("Seleccione una opcion:");
		System.out.println("1.   Crear nueva Planta.");
		System.out.println("2.  Ver plantas.");
		System.out.println("99.  Volver al menu Principal");
	}
	
	public void mostrarMenuPrincipalEjemplares() {
		System.out.println("Seleccione una opcion:");
		System.out.println("1.  Crear nuevo Ejemplar.");
		System.out.println("3.  Leer detalles de Ejemplar.");
		System.out.println("99.  Volver al menu Principal");
	}

	public void mostrarMenuGestionPrincipal() {
	    Scanner in = new Scanner(System.in);
        System.out.println("Gestion de Vivero");
        String opcion = "";
        int opcionInt = -1;
        do {
        	mostrarMenuPrincipal();
            opcion = in.next().trim();
            
            try {
                
            	opcionInt= Integer.parseInt(opcion.trim());
               
           } catch (NumberFormatException e) {
             
               opcionInt = -1; 
           }
            if (opcionInt == 99) {
                System.out.println("Saliendo...");
                System.exit(0);
            }
else if (opcionInt < 1 || opcionInt > 2 ) {
                System.out.println("Opción incorrecta.");
                continue;
            }
	
            switch (opcionInt) {
            case 1:
            	mostrarMenuGestionPlantas();
            	break;
            case 2:
            mostrarMenuGestionEjemplares();
            break;
            
            	default:
            		break;
            }}while(opcionInt!=99);
	
	
	}
	public void mostrarMenuGestionPlantas() {
        Scanner in = new Scanner(System.in);
        System.out.println("Gestion de plantas");
        String opcion = "";
        int opcionInt = -1;
        do {
        	 mostrarMenuPrincipalPlantas();
            opcion = in.next().trim();
            
            try {
                
            	opcionInt= Integer.parseInt(opcion.trim());
               
           } catch (NumberFormatException e) {
             
               opcionInt = -1; 
           }

            if (opcionInt < 1 || opcionInt > 2) {
                System.out.println("Opción incorrecta.");
                continue;
            }
	
            switch (opcionInt) {
            case 1:

           String nombreComun;
		   String codigo;
		   String nombreCientifico;
		   Utils utils = new Utils(con);

	       do {
	        System.out.println("Dame codigo de una nueva planta (FORMATO DE 4 NUMEROS [0000])");
	         codigo = in.next().trim();
	         if(utils.existeCodigoPlanta(codigo)) {
	        	 System.out.println("Ese codigo ya existe, no es posible");
	         }
	         else if(!codigo.matches("\\d{4}")){
	        	 System.out.println("Formato no valido");
	         }
	       } while(!codigo.matches("\\d{4}")||utils.existeCodigoPlanta(codigo) );
	       

	        do {
	        System.out.println("Dame nombre comun de una planta");
	         nombreComun = in.next().trim().toUpperCase();
	         if(!nombreComun.matches("[A-ZÁÉÍÓÚÑ][a-zA-ZÁÉÍÓÚÑáéíóúñ\\s]{2,99}")) {
	        	 System.out.println("Dato invalido. Solo se aceptan letras y nombres de entre 3 y 100 caracteres");
	         }
	        }while(!nombreComun.matches("[A-ZÁÉÍÓÚÑ][a-zA-ZÁÉÍÓÚÑáéíóúñ\\s]{2,99}"));
	        	
	        do {
	            System.out.println("Dame nombre científico de una planta:");
	            nombreCientifico = in.next().trim().toUpperCase();

	            if (!nombreCientifico.matches("[A-ZÁÉÍÓÚÑ][a-zA-ZÁÉÍÓÚÑáéíóúñ\\s]{2,99}")) {
	                System.out.println("Dato inválido. Solo se aceptan letras y nombres de entre 3 y 100 caracteres.");
	            }
	        } while (!nombreCientifico.matches("[A-ZÁÉÍÓÚÑ][a-zA-ZÁÉÍÓÚÑáéíóúñ\\s]{2,99}"));


	        try {
	        	 plantaDAO.insertarPlanta(codigo, nombreComun, nombreCientifico);;
              
            } catch (Exception e) {
                System.out.println("Se ha producido una excepcion: " + e.getMessage());
            }
	       
            break;
            	 
            	 
            	
            case 2:
            	 List<Planta> plantas = plantaDAO.obtenerPlantas();
                 
                 System.out.println(Utils.obtenerEncabezado());
                 for (Planta pl : plantas) {
                     System.out.println(pl);
                 }
            	break;
            case 99:
                break;
            default:
                System.out.println("Error al seleccionar filtro.");
                break;
            }
            }while(opcionInt!=99);
	
	}

	public void mostrarMenuGestionEjemplares() {
        Scanner in = new Scanner(System.in);
        System.out.println("Gestion de ejemplares");
        String opcion = "";
        int opcionInt = -1;
        do {
        	 mostrarMenuPrincipalEjemplares();
            opcion = in.next().trim();
            
            try {
                
            	opcionInt= Integer.parseInt(opcion.trim());
               
           } catch (NumberFormatException e) {
             
               opcionInt = -1; 
           }

            if (opcionInt < 1 || opcionInt > 2) {
                System.out.println("Opción incorrecta.");
                continue;
            }
	
            switch (opcionInt) {
            case 1:
            	String codigoplanta;
        		String nombre;
        		Scanner in1 = new Scanner(System.in);
        		boolean existe = false;
        		Utils utils = new Utils(con);
        	      do {
        	            System.out.println("Dame código de una planta:");
        	            codigoplanta = in1.next().trim();
        	            existe = utils.existeCodigoPlanta(codigoplanta);

        	            if (!existe) {
        	                System.out.println("El código no existe. Por favor, ingrese un código valido.");
        	            } 
        	        } while (!existe);
        	      long id = utils.generarIdEjemplar();
        			String nombregenerado = utils.generarNombreEjemplar(codigoplanta);
        			nombre = id + "_" + nombregenerado;
        			long idMensj = utils.generarIdMensaje();
        			LocalDate fechaHora = LocalDate.now();
        			String usuario="";
        			ejemplarDao.nuevoEjemplar(id,nombre,codigoplanta);
        			 mensajeDAO.nuevoMensaje(idMensj, fechaHora, utils.generarmensaje(), id, 58446725L);
            break;
            	 
            	 
            	
            case 2:
            	 List<Planta> plantas = plantaDAO.obtenerPlantas();
                 
                 System.out.println(Utils.obtenerEncabezado());
                 for (Planta pl : plantas) {
                     System.out.println(pl);
                 }
            	break;
            case 99:
                break;
            default:
                System.out.println("Error al seleccionar filtro.");
                break;
            }
            }while(opcionInt!=99);
	
	}

}
	
	