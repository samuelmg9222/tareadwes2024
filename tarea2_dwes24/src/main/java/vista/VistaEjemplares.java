package vista;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.Ejemplar;
import modelo.Mensaje;
import principal.ConexionBD;
import principal.Controlador;
import utilidades.Utils;

public class VistaEjemplares {
	private Connection con;
	private static VistaEjemplares portalvistaEjeplares;
	public VistaEjemplares(Connection con) {
		this.con = con;
		
	}
	public static VistaEjemplares getPortalvistaEjemplares() {
		if (portalvistaEjeplares==null)
			portalvistaEjeplares=new VistaEjemplares(ConexionBD.getInstance().getConnection());
		return portalvistaEjeplares;
	}
	public void mostrarMenuGestionEjemplares(Long idpersona) {
		
		Scanner in = new Scanner(System.in);
		System.out.println("Gestion de ejemplares");
		String opcion = "";
		int opcionInt = -1;
		do {
			System.out.println("\n\tSeleccione una opción:\n");
			System.out.println("\t1. Registrar ejemplar.\n");
			System.out.println("\t2. Filtrar ejemplares por tipo de planta .\n");
			System.out.println("\t3.  Ver mensajes para ejemplar .\n");
			System.out.println("\t4.  Crear Mensaje.\n");
			System.out.println("\t5.  Filtrar mensajes.\n");
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
					Controlador.getServicios().getServiciosMensaje().InsertarMensaje(ms,idpersona);
					
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
		

	
			case 4:
				String codigoEjemplar;

				
			

				
					System.out.println("Dame código del ejemplar sobre el que quieres escribir mensaje:");
					codigoEjemplar = in.next().trim();
					
					if(Controlador.getServicios().getServiciosEjemplar().existeCodigoEjemplar(codigoEjemplar)) {
				Long idej = Long.parseLong(codigoEjemplar);
				Mensaje ms =new Mensaje(Controlador.getServicios().getServiciosMensaje().generarIdMensaje(), LocalDateTime.now(), Utils.generarmensaje(), idej,idpersona);
				Controlador.getServicios().getServiciosMensaje().InsertarMensaje(ms,idpersona);
				
					}else System.out.println("Codigo no valido");
				break;

			case 5:
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
						Long codigoUs;
						
							System.out.println("Dame el id del usuario para ver sus mensajes");
							cod = in.next().trim();
							 

						    if(Controlador.getServicios().getServiciosPersona().existeUsuario(cod)) {
						    	try {
						    		codigoUs = Long.parseLong(cod);
						        List<Mensaje> mensajespersona= Controlador.getServicios().getServiciosMensaje().filtrarMensajePersona(codigoUs);
						        System.out.println("Mensajes del usuario con nombre "+ Controlador.getServicios().getServiciosPersona().obtenerNombreUsuario(codigoUs)+ " e id "+codigoUs);
						        for (Mensaje mns : mensajespersona) {
									System.out.println(mns);
								}
						    } catch (NumberFormatException e) {
						        System.out.println("No se ha podido obtener los mensjaes. Id introuducido no valido" + e.getMessage());
						    }
					}else {
						System.out.println("No existe persona con ese codigo");
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
							    }else {
							    	System.out.println("El rango de fechas elegido no es valido");
							    }
								 }catch (NumberFormatException e) {
							        System.out.println("Error al convertir el id de usuario a número: " + e.getMessage());
							    } catch (DateTimeParseException e) {
					                System.out.println("Formato de fecha no válido. Debes de usar el formato yyyy-MM-dd.");
					                
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
				System.out.println("Opción incorrecta.");
				break;

			}

		} while (opcionInt != 99);
	}
	
}
