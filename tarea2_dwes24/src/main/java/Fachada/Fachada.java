package Fachada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import dao.EjemplarDAO;
import dao.MensajeDAO;
import dao.PersonaDAO;
import dao.PlantaDAO;
import modelo.Ejemplar;
import modelo.Mensaje;
import modelo.Planta;
import principal.ConexionBD;
import principal.Controlador;
import utilidades.Utils;

public class Fachada {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public Fachada(Connection c) {
		this.con = c;
		
	}
	private static Fachada portal;
	Utils utils = new Utils(con);
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
				List<Planta> plantas=Controlador.getServicios().getServiciosPlanta().findAll();
						

				System.out.println(Utils.obtenerEncabezado());
				for (Planta pl : plantas) {
					System.out.println(pl);
				}
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
			           
			     

			        
			        Planta plantaAntigua = Controlador.getServicios().getServiciosPlanta().obtenerdatosplanta(codigo)); 
			        
			        System.out.println("El nombre común actual es: " + plantaAntigua.getNombrecomun());
			        System.out.println("El nombre científico actual es: " + plantaAntigua.getNombrecientifico());

			   
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
				
				do {
					System.out.println("Dame código de una planta:");
					codigoplanta = in1.next().trim();
					existe = plantaDAO.existeCodigoPlanta(codigoplanta);

					if (!existe) {
						System.out.println("El código no existe. Por favor, ingrese un código valido.");
					}
				} while (!existe);
				long id = ejemplarDao.generarIdEjemplar();
				String nombregenerado = ejemplarDao.generarNombreEjemplar(codigoplanta);
				nombre = id + "_" + nombregenerado;
				long idMensj = mensajeDAO.generarIdMensaje();
				 LocalDateTime fechaH = LocalDateTime.now();
				String usuario = "";
				try {
					ejemplarDao.nuevoEjemplar(id, nombre, codigoplanta);
					mensajeDAO.nuevoMensaje(idMensj, fechaH, utils.generarmensaje(), id, 58446725L);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;

			case 2:
				String codigo = "";
				Set<String> codigos = new HashSet<>();

				do {
					System.out
							.println("Dame código de una planta para ver sus ejemplares: (INTRODUCE 9999 para salir)");
					codigo = in.next().trim();
					if (codigo.equals("9999")) {

						break;
					}

					if (codigos.contains(codigo)) {
						System.out.println("ese código ya lo has introducido:");
					} else {

						if (plantaDAO.existeCodigoPlanta(codigo)) {
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

					}
				} while (!codigo.equals("9999"));

				if (!codigos.isEmpty()) {
					for (String c : codigos) {

						List<Ejemplar> ejemplares = ejemplarDao.obtenerEjemplaresPorCodigoPlanta(c);
						if (ejemplares.isEmpty()) {
							System.out.println("\nLa planta con codigo" + c + " se encuentra sin ejemplares");
						} else {
							System.out.println("\nEjemplares con código de planta " + c + ":");
							for (Ejemplar ej : ejemplares) {
								System.out.println(ej);
							}

						}
					}
				}

				break;
				
				
			case 3:
			
				String idEjem;
				List <Ejemplar> ejemplares =ejemplarDao.verEjemplares();
				for(Ejemplar c: ejemplares) {
					System.out.println("\t"+c);
				}
				do {
					System.out.println("Dame un id de ejemplar para ver mensajes asociados:");
					idEjem = in.next().trim();
					existe = ejemplarDao.existeCodigoEjemplar(idEjem);

					if (!existe) {
						System.out.println("El id no existe. Por favor, ingrese un id valido.");
					}
				} while (!existe);
				
				List <Mensaje> mensajesAsociados =mensajeDAO.filtrarMensajeEjemplar(idEjem);
				if (mensajesAsociados.isEmpty()) {
				    System.out.println("Ese ejemplar no tiene mensajes asociados");
				} else {
				    for (Mensaje m : mensajesAsociados) {
				        System.out.println("Autor: " + m.getIdPersona() + " Fecha y hora: " + m.getFechaHora());
				    }
				}  
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
				boolean existe = false;
				Utils utils = new Utils(con);
				LocalDateTime fechaHora = LocalDateTime.now();

				do {
					System.out.println("Dame código del ejemplar sobre el que quieres escribir mensaje:");
					codigoEjemplar = in1.next().trim();
					existe = ejemplarDao.existeCodigoEjemplar(codigoEjemplar);

					if (!existe) {
						System.out.println("El código no existe. Por favor, ingrese un código valido.");
					}
				} while (!existe);
				Long idej = Long.parseLong(codigoEjemplar);

				mensajeDAO.nuevoMensaje(mensajeDAO.generarIdMensaje(), fechaHora, utils.generarmensaje(), idej, 58446725L);
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
						do {
							System.out.println("Dame el id del usuario para ver sus mensajes");
							cod = in.next().trim();
							 if (!cod.matches("\\d+")) { 
						            System.out.println("El id de usuario debe contener solo números.");
						            continue;
						        }

						        if (!personaDAO.existeUsuario(cod)) {
						            System.out.println("No existe dicho usuario");
						        }
						    } while (!cod.matches("\\d+") || !personaDAO.existeUsuario(cod));

						    try {
						        codigo = Long.parseLong(cod);
						        List<Mensaje> mensajespersona=  mensajeDAO.filtrarMensajePersona(codigo);
						        System.out.println("Mensades del usuario con id "+codigo);
						        for (Mensaje ms : mensajespersona) {
									System.out.println(ms);
								}
						    } catch (NumberFormatException e) {
						        System.out.println("Error al convertir el id de usuario a número: " + e.getMessage());
						    }
						break;
					case 2:
						String cod2;
						Long codigo2;
					
						do {
							System.out.println("Dame codigo de la planta sobre el que se han escrito mensajes");
							cod2 = in.next().trim();
							if (!plantaDAO.existeCodigoPlanta(cod2)) {
								System.out.println("No existe planta con ese codigo");

							}
						} while (!plantaDAO.existeCodigoPlanta(cod2)||!cod2.matches("\\d+"));
						
						
						 try {
							 codigo2 = Long.parseLong(cod2);
						        List<Mensaje> mensajesplanta=  mensajeDAO.filtrarMensajeTipoPlanta(codigo2);
						        System.out.println("Mensades de la planta con id "+codigo2);
						        for (Mensaje ms : mensajesplanta) {
									System.out.println(ms);
								}
						    } catch (NumberFormatException e) {
						        System.out.println("Error al convertir el id de usuario a número: " + e.getMessage());
						    }
						
						
						
						
						break;
					case 3:
						  LocalDate fechaInicio = null;
					        LocalDate fechaFin = null;
					        LocalDate fechaActual = LocalDate.now(); 
					        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

					        do {
					            try {
					                System.out.println("Introduce la fecha de inicio (formato yyyy-MM-dd):");
					                String fechaInicioStr = in.next().trim();
					                fechaInicio = LocalDate.parse(fechaInicioStr, formatter);

					                
					                if (fechaInicio.isAfter(fechaActual)) {
					                    System.out.println("La fecha de inicio no puede ser posterior al día actual. Intenta de nuevo.");
					                    fechaInicio = null; 
					                    continue; 
					                }

					                System.out.println("Introduce la fecha de fin (formato yyyy-MM-dd):");
					                String fechaFinStr = in.next().trim();
					                fechaFin = LocalDate.parse(fechaFinStr, formatter);

					                
					                if (fechaInicio.isAfter(fechaFin)) {
					                    System.out.println("La fecha de inicio debe ser anterior o igual a la fecha de fin. Intenta de nuevo.");
					                    fechaInicio = null;
					                    fechaFin = null;
					                }

					            } catch (DateTimeParseException e) {
					                System.out.println("Formato de fecha no válido. Asegúrate de usar el formato yyyy-MM-dd.");
					                fechaInicio = null;
					                fechaFin = null;
					            }
					        } while (fechaInicio == null || fechaFin == null);

					        
						
					        try {
								 
							        List<Mensaje> mensajesfecha=  mensajeDAO.filtrarMensajeRangoFechas(fechaInicio,fechaFin);
							        System.out.println("Rango de fechas elegido: " + fechaInicio + " a " + fechaFin);
							        for (Mensaje ms : mensajesfecha) {
										System.out.println(ms);
									}
							    } catch (NumberFormatException e) {
							        System.out.println("Error al convertir el id de usuario a número: " + e.getMessage());
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
}
