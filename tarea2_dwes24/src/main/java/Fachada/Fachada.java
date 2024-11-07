package Fachada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import controlador.Controlador;
import dao.EjemplarDAO;
import dao.MensajeDAO;
import dao.PersonaDAO;
import dao.PlantaDAO;
import modelo.Ejemplar;
import modelo.Mensaje;
import modelo.Planta;
import utilidades.ConexionBD;
import utilidades.Utils;

public class Fachada {
	/*
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public Fachada(Connection c) {
		this.con = c;
		
	}
	private static Fachada portal;
	Utils utils = new Utils(ConexionBD.getInstance().getConnection());
	PersonaDAO personaDAO = new PersonaDAO(con);
	EjemplarDAO ejemplarDao = new EjemplarDAO(ConexionBD.getInstance().getConnection());
	PlantaDAO plantaDAO = new PlantaDAO(ConexionBD.getInstance().getConnection());
	MensajeDAO mensajeDAO = new MensajeDAO(ConexionBD.getInstance().getConnection());

	
	public static Fachada getPortal() {
		if (portal==null)
			portal=new Fachada(ConexionBD.getInstance().getConnection());
		return portal;
	}
	public void mostrarMenuPrincipal() {
		System.out.println("\n\tSeleccione una opción:\n");
		System.out.println("\t1.  Gestionar Plantas.\n");
		System.out.println("\t2.  Gestionar Ejemplares.\n");
		System.out.println("\t3.  Gestionar Mensajes.\n");
		System.out.println("\t99.  Salir\n");
	}

	public void mostrarMenuPrincipalPlantas() {
		System.out.println("\n\tSeleccione una opción:\n");
		System.out.println("\t1.  Crear nueva Planta (CU4_A).\n");
		System.out.println("\t2.  Ver plantas (CU1).\n");
		System.out.println("\t2.  Modificar planta (CU4_B).\n");
		System.out.println("\t99.  Volver al menú Principal\n");
	}

	public void mostrarMenuPrincipalEjemplares() {
		System.out.println("\n\tSeleccione una opción:\n");
		System.out.println("\t1. Registrar ejemplar (CU5_A).\n");
		System.out.println("\t2. Filtrar ejemplares por tipo de planta (CU5_B).\n");
		System.out.println("\t3.  Ver mensajes para ejemplar (CU5_C) .\n");
		System.out.println("\t99.  Volver al menú Principal\n");
	}

	public void mostrarMenuPrincipalMensajes() {
		System.out.println("\n\tSeleccione una opción:\n");
		System.out.println("\t1.  Crear Mensaje.\n");
		System.out.println("\t99.  Volver al menú Principal\n");
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

				opcionInt = Integer.parseInt(opcion.trim());

			} catch (NumberFormatException e) {

				opcionInt = -1;
			}
			if (opcionInt == 99) {
				System.out.println("Saliendo...");
				System.exit(0);
			} else if (opcionInt < 1 || opcionInt > 3) {
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
			case 3:
				mostrarMenuGestionMensajes();
				break;

			default:
				break;
			}
		} while (opcionInt != 99);

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

				    
				   
				        System.out.println("Dame código de una nueva planta (FORMATO DE 4 NÚMEROS [0000]):");
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
				
				listadoplantas();
				
				break;

			case 3:
		
codigo=""; nombreComun=""; nombreCientifico="";
List<Planta> plantas2=Controlador.getServicios().getServiciosPlanta().findAll();


System.out.println(Utils.obtenerEncabezado());
for (Planta pl : plantas2) {
	System.out.println(pl);
}
			        try {
			            System.out.println("Dame código de la planta que desea modificar (FORMATO DE 4 NÚMEROS [0000]):");
			            codigo = in.next().trim();

			        System.out.println("Dame nombre común que desea poner a la planta. Si no desea modificarlo, introduce su nombre como está:");
			        nombreComun = in.next().trim().toUpperCase();

			        System.out.println("Dame nombre científico de la planta. Si no desea modificarlo, introduce su nombre como está:");
			        nombreCientifico = in.next().trim().toUpperCase();
			        Planta pl=new Planta(codigo,nombreComun,nombreCientifico);
				    
			        if (Controlador.getServicios().getServiciosPlanta().verificarModificacion(pl)) {
			        	 Controlador.getServicios().getServiciosPlanta().modificarPlanta(pl); 
				            System.out.println(nombreComun + " (" + nombreCientifico + ") ingresada con éxito: código " + codigo + ".");
			        	
			        }else {
			        	System.out.println("No se ha podido relaizar la modificación");
			        }
			        } catch (Exception e) {
			            System.out.println("Se ha producido una excepción: " + e.getMessage());
			        }
			case 99:
				break;
			default:
				System.out.println("Error al seleccionar filtro.");
				break;
			}
		} while (opcionInt != 99);

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
				String codigoplanta;
				String nombre;
				
				Scanner in1 = new Scanner(System.in);
				boolean existe = false;
				
			
					System.out.println("Dame código de una planta:");
					codigoplanta = in1.next().trim();
					
				
				
				 LocalDateTime fechaH = LocalDateTime.now();
				Long usuario =1L;
				 if(Controlador.getServicios().getServiciosPlanta().existeCodigoPlanta(codigoplanta)) {
				try {
					Long idEj = Controlador.getServicios().getServiciosEjemplar().generarIdEjemplar();
					Ejemplar ej=new Ejemplar(idEj, (idEj +"_"+Controlador.getServicios().getServiciosEjemplar().generarNombreEjemplar(codigoplanta)));
					Controlador.getServicios().getServiciosEjemplar().InsertarEjemplar(ej, codigoplanta);
					
					
					Mensaje ms=new Mensaje(Controlador.getServicios().getServiciosMensaje().generarIdMensaje(),fechaH,Utils.generarmensaje(),idEj,usuario);
					Controlador.getServicios().getServiciosMensaje().InsertarMensaje(ms);
					
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}}else {
					
				}System.out.println("NO EXISTE EL CODIGO DE PLANTA");
				break;

			case 2:
				String codigo = "";
				ArrayList<String> codigos = new ArrayList<>();

				do {
					System.out.println("Dame código de una planta para ver sus ejemplares: (INTRODUCE 9999 para salir)");
					codigo = in.next().trim();
					Controlador.getServicios().getServiciosEjemplar().procesarCodigo(codigo);

						if (Controlador.getServicios().getServiciosPlanta().existeCodigoPlanta(codigo)) {
							codigos.add(codigo);
							System.out.println("Código válido, ¿desea alguno más? (S/N)");
							String opc;
							do {
								opc = in.next().toUpperCase().trim();
								if (!opc.equals("S") && !opc.equals("N")) {
									System.out.println("Introduce una opción válida; (S/N)");
								}
							} while (!opc.equals("S") && !opc.equals("N"));
							if (opc.equals("N")) {
								break;
							}
						} else {
							System.out.println("Ese código de planta no existe");
						}

					
				} while (!codigo.equals("9999"));

				if (!codigos.isEmpty()) {
					for (String c : codigos) {

						List<Ejemplar> ejemplares = Controlador.getServicios().getServiciosEjemplar().obtenerEjemplaresPorCodigo(c);
						if (ejemplares.isEmpty()) {
							System.out.println("\nLa planta con codigo" + c + " se encuentra sin ejemplares");
						} else {
							System.out.println("\nEjemplares con código de planta " + c + ":");
							for (Ejemplar ejmp : ejemplares) {
								System.out.println(ejmp);
							}

						}
					}
				}

				break;
				
				
			case 3:
			
				String idEjem;
				List <Ejemplar> ejemplares =Controlador.getServicios().getServiciosEjemplar().findAll();
				for(Ejemplar c: ejemplares) {
					System.out.println("\t"+c);
				}
				
					System.out.println("Dame un id de ejemplar para ver mensajes asociados:");
					idEjem = in.next().trim();
					

					
				if(Controlador.getServicios().getServiciosEjemplar().existeCodigoEjemplar(idEjem)) {
				try {
				List <Mensaje> mensajesAsociados =Controlador.getServicios().getServiciosMensaje().filtrarMensajeEjemplar(idEjem);
				if (mensajesAsociados.isEmpty()) {
				    System.out.println("Ese ejemplar no tiene mensajes asociados");
				} else {
				    for (Mensaje m : mensajesAsociados) {
				        System.out.println("Autor: " + m.getIdPersona() + " Fecha y hora: " + m.getFechaHora());
				    }
				}}catch(Exception e) {
					e.printStackTrace();
				}
					}else
						System.out.println("Codigo no valido");
				    break;
			case 99:
				break;
			default:
				System.out.println("Error al seleccionar filtro.");
				break;
			}
		} while (opcionInt != 99);

	}

	public void mostrarMenuGestionMensajes() {
		Scanner in = new Scanner(System.in);
		System.out.println("Gestion de ejemplares");
		String opcion = "";
		int opcionInt = -1;
		do {
			mostrarMenuPrincipalMensajes();
			opcion = in.next().trim();

			try {

				opcionInt = Integer.parseInt(opcion.trim());

			} catch (NumberFormatException e) {

				opcionInt = -1;
			}

			if (opcionInt == 99) {
				System.out.println("Volviendo...");

			} else if (opcionInt < 1 || opcionInt > 2) {
				System.out.println("Opción incorrecta.");
				continue;
			}

			switch (opcionInt) {
			case 1:
				String codigoEjemplar;

				Scanner in1 = new Scanner(System.in);
				
			

				
					System.out.println("Dame código del ejemplar sobre el que quieres escribir mensaje:");
					codigoEjemplar = in1.next().trim();
					
					if(Controlador.getServicios().getServiciosEjemplar().existeCodigoEjemplar(codigoEjemplar)) {
				Long idej = Long.parseLong(codigoEjemplar);
				Mensaje ms =new Mensaje(Controlador.getServicios().getServiciosMensaje().generarIdMensaje(), LocalDateTime.now(), Utils.generarmensaje(), idej, 58446725L);
				mensajeDAO.nuevoMensaje(ms);
				
					}else System.out.println("Codigo no valido");
				break;

			case 2:
				do {

					System.out.println("\n\tSeleccione una opción:\n");
					System.out.println("\t1.  Filtrar por usuario.\n");
					System.out.println("\t2.  Filtrar por tipoplanta.\n");
					System.out.println("\t3.  Filtrar por Fechas.\n");
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
						String cod;
						Long codigo;
						
							System.out.println("Dame el id del usuario para ver sus mensajes");
							cod = in.next().trim();
							 

						    if(Controlador.getServicios().getServiciosPersona().existeUsuario(cod)) {
						    	try {
						        codigo = Long.parseLong(cod);
						        List<Mensaje> mensajespersona= Controlador.getServicios().getServiciosMensaje().filtrarMensajePersona(codigo);
						        System.out.println("Mensades del usuario con id "+codigo);
						        for (Mensaje mns : mensajespersona) {
									System.out.println(mns);
								}
						    } catch (NumberFormatException e) {
						        System.out.println("Error al convertir el id de usuario a número: " + e.getMessage());
						    }
					}
						break;
					case 2:
						String cod2;
						Long codigo2;
					
						
							System.out.println("Dame codigo de la planta sobre el que se han escrito mensajes");
							cod2 = in.next().trim();
							
						
						
						if(Controlador.getServicios().getServiciosPlanta().existeCodigoPlanta(cod2)) {
						 try {
							 codigo2 = Long.parseLong(cod2);
						        List<Mensaje> mensajesplanta= Controlador.getServicios().getServiciosMensaje().filtrarMensajeTipoPlanta(codigo2);
						        System.out.println("Mensades de la planta con id "+codigo2);
						        for (Mensaje msjs : mensajesplanta) {
									System.out.println(msjs);
								}
						    } catch (NumberFormatException e) {
						        System.out.println("Error al convertir el id de usuario a número: " + e.getMessage());
						    }
						
						}
						
						
						break;
					case 3:
						  LocalDate fechaInicio = null;
					        LocalDate fechaFin = null;
					        LocalDate fechaActual = LocalDate.now(); 
					        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

					      
					            try {
					                System.out.println("Introduce la fecha de inicio (formato yyyy-MM-dd):");
					                String fechaInicioStr = in.next().trim();
					                fechaInicio = LocalDate.parse(fechaInicioStr, formatter);

					                System.out.println("Introduce la fecha de fin (formato yyyy-MM-dd):");
					                String fechaFinStr = in.next().trim();
					                fechaFin = LocalDate.parse(fechaFinStr, formatter);
					          
					    
								 if(Controlador.getServicios().getServiciosMensaje().verificarfecha( fechaInicio, fechaFin, fechaActual)) {
							        List<Mensaje> mensajesfecha=  Controlador.getServicios().getServiciosMensaje().filtrarMensajeRangoFechas(fechaInicio,fechaFin);
							        System.out.println("Rango de fechas elegido: " + fechaInicio + " a " + fechaFin);
							        for (Mensaje ms : mensajesfecha) {
										System.out.println(ms);
									}
							    } }catch (NumberFormatException e) {
							        System.out.println("Error al convertir el id de usuario a número: " + e.getMessage());
							    } catch (DateTimeParseException e) {
					                System.out.println("Formato de fecha no válido. Deberias haber usado el formato yyyy-MM-dd.");
					                fechaInicio = null;
					                fechaFin = null;
					            }
							
					            
						
						break;

					default:
						System.out.println("Opción incorrecta.");
						break;
					}

				} while (opcionInt != 99);
				break;

			case 99:
				break;
			default:
				System.out.println("Error al seleccionar filtro.");
				break;

			}

		} while (opcionInt != 99);
	}
	
	
	
	
	
	
	
	public void listadoplantas() {
		List<Planta> plantas=Controlador.getServicios().getServiciosPlanta().findAll();
		

		System.out.println(Utils.obtenerEncabezado());
		for (Planta pl : plantas) {
			System.out.println(pl);
		}
	}
	*/
}
