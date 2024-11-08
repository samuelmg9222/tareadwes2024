package vista;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controlador.Controlador;
import modelo.Ejemplar;
import modelo.Mensaje;
import modelo.Persona;
import modelo.Planta;
import utilidades.ConexionBD;
import utilidades.Utils;

public class VistaEjemplares {
	private Connection con;
	private static VistaEjemplares portalvistaEjeplares;

	public VistaEjemplares(Connection con) {
		this.con = con;

	}

	public static VistaEjemplares getPortalvistaEjemplares() {
		if (portalvistaEjeplares == null)
			portalvistaEjeplares = new VistaEjemplares(ConexionBD.getInstance().getConnection());
		return portalvistaEjeplares;
	}

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

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

			} else if (opcionInt < 1 || opcionInt > 5) {
				System.out.println("Opción incorrecta.");
				continue;
			}

			switch (opcionInt) {
			case 1:
				List<Planta> plantas3 = new ArrayList<Planta>();
				if (Controlador.getServicios().getServiciosPlanta().findAll() != null
						&& !Controlador.getServicios().getServiciosPlanta().findAll().isEmpty()) {
					plantas3 = Controlador.getServicios().getServiciosPlanta().findAll();

					System.out.println("   " + Utils.obtenerEncabezado());
					int i = 1;

					for (Planta pl : plantas3) {

						System.out.println(i + ": " + pl.getNombrecomun() + "\t" + pl.getNombrecientifico());
						i++;
					}
				} else {
					System.out.println("No se ha podido mostrar el listado o la lista está vacía.");
					break;
				}
				int ind = 0;
				do {
				try {
					System.out.println("Dame el numero (indice) de la planta que desea modificar: 9999 para salir");
					String indice = in.next().trim();

					 ind = Integer.parseInt(indice);
					 
					 if(ind==9999) {
						 break;
					 }
					Long idEj;

					LocalDateTime fechaH = LocalDateTime.now();

					if (Controlador.getServicios().getServiciosPlanta()
							.existeCodigoPlanta(plantas3.get(ind - 1).getCodigo())) {

						if (Controlador.getServicios().getServiciosEjemplar().generarIdEjemplar() == 0) {
							System.out.println("Error al generar idEjemplar. No se ha podido completar el proceso");
							break;
						} else {
							idEj = Controlador.getServicios().getServiciosEjemplar().generarIdEjemplar();
							if (Controlador.getServicios().getServiciosEjemplar()
									.generarNombreEjemplar(plantas3.get(ind - 1).getCodigo()).isBlank()) {
								System.out.println(
										"Error al generar nombre del ejemplar. No se ha podido completar el proceso");
							} else {
								Ejemplar ej = new Ejemplar(idEj,
										(idEj + "_" + Controlador.getServicios().getServiciosEjemplar()
												.generarNombreEjemplar(plantas3.get(ind - 1).getCodigo())));

								if (Controlador.getServicios().getServiciosEjemplar().InsertarEjemplar(ej,
										plantas3.get(ind - 1).getCodigo()) == 0) {
									System.out.println("No se ha podido insertar el ejemplar");
									break;
								} else {
									if (Controlador.getServicios().getServiciosMensaje().generarIdMensaje() == 0) {
										System.out.println("No se ha podido generar el idmensaje");
										
									} else {
										Mensaje ms = new Mensaje(
												Controlador.getServicios().getServiciosMensaje().generarIdMensaje(),
												fechaH, Utils.generarmensaje(idpersona), idEj, idpersona);
										if (Controlador.getServicios().getServiciosMensaje().InsertarMensaje(ms,
												idpersona) == 0) {
											System.out.println("No se ha podido insertar el mensaje");
										} else {
											System.out.println(
													"Se ha registrado el ejemplar correctamente.  Tambien se ha registrado un mensaje: "
															+ ms.getMensaje());
										}
									}
								}
							}
						}
					} else
						System.out.println("No existe el codigo introducido");
				} catch (Exception e) {
					System.out.println(
							"No se ha podido completar el proceso. El indice tiene que ser un numero de los mostrados");
				}
				
				}while(ind!=9999);
				break;

			
			
			
			case 2:
				
				ArrayList<String> codigos = new ArrayList<>();
				
				List<Planta> plantas2 = new ArrayList<Planta>();
				
				if (Controlador.getServicios().getServiciosPlanta().findAll() != null
						&& !Controlador.getServicios().getServiciosPlanta().findAll().isEmpty()) {
					plantas2 = Controlador.getServicios().getServiciosPlanta().findAll();

					System.out.println("   " + Utils.obtenerEncabezado());
					int i = 1;

					for (Planta pl : plantas2) {

						System.out.println(i + ": " + pl);
						i++;
					}
				} else {
					System.out.println("No se ha podido mostrar el listado o la lista está vacía.");
					break;
				}
				

				int indicePlanta;
do {
				do {
					try {
						System.out.println(
								"Dame el numero (índice) de la planta sobre el que quieres crear una planta (9999 para salir)");
						indicePlanta = -1;
						String indice = in.next().trim();
						indicePlanta = Integer.parseInt(indice);
						if (!(indicePlanta > 0 && indicePlanta <= plantas2.size() || indicePlanta == 9999)) {

							System.out.println("Por favor, elige un número válido dentro del rango.");
							continue;
						}

						if (indice.equals("9999")) {
							break;
						}

						if (Controlador.getServicios().getServiciosEjemplar().procesarCodigo(plantas2.get(indicePlanta - 1).getCodigo(), codigos)) {

							if (Controlador.getServicios().getServiciosPlanta().existeCodigoPlanta(plantas2.get(indicePlanta - 1).getCodigo())) {

								System.out.println("Código válido, ¿desea ingresar otro código? (S/N)");

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
							}else {
								System.out.println("Codigo no valido");
							}
						}else {
							System.out.println("Se ha producido un error al procesar el codigo");
						}
					} catch (NumberFormatException e) {
						System.out.println("Introduce un número válido.");
						continue;
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} while (true);

				if (!codigos.isEmpty()) {
					for (String c : codigos) {
						List<Ejemplar> ejemplares = Controlador.getServicios().getServiciosEjemplar().obtenerEjemplaresPorCodigo(c);
						if (ejemplares.isEmpty()) {
							System.out.println("\nLa planta con código " + c + " se encuentra sin ejemplares");
						} else {
							System.out.println("\nEjemplares con código de planta " + c + ":");
							for (Ejemplar ejmp : ejemplares) {
								System.out.println(ejmp);
							}
						}
					}
				}
				 in.nextLine();
}while(indicePlanta==9999);
				break;

			case 3:
				Long idEjem = 0L;
				String indc,idEjemStr = "";
				int j = 1;
				int opc = 0;
				List<Ejemplar> ejemplares = new ArrayList<Ejemplar>();
				List<Mensaje> mensajesAsociados = new ArrayList<Mensaje>();
				if (Controlador.getServicios().getServiciosEjemplar().findAll() != null
						&& !Controlador.getServicios().getServiciosEjemplar().findAll().isEmpty()) {

					ejemplares = Controlador.getServicios().getServiciosEjemplar().findAll();
					for (Ejemplar c : ejemplares) {
						System.out.println(j + ".  id: " + c.getId() + "  nombre: " + c.getNombre());
						j++;
					}
do {
					System.out.println("\nDame un indice (NUMERO DE LISTA) para ver mensajes asociados a ese ejemplar: (9999 para salir)");
					indc = in.next().trim();
					 

					try {
						opc = Integer.parseInt(indc);
						idEjem = ejemplares.get(opc - 1).getId();
						idEjemStr = ejemplares.get(opc - 1).getId().toString();
					} catch (Exception e) {
						if(opc!=9999) {
						System.out.println("Error: el indice debe de ser un numero y dentro del rango");
						continue;
					}
					}

					if (Controlador.getServicios().getServiciosEjemplar().existeCodigoEjemplar(idEjemStr)) {
						try {
							if (!(Controlador.getServicios().getServiciosMensaje().filtrarMensajeEjemplar(idEjem) != null
									&& !Controlador.getServicios().getServiciosMensaje().filtrarMensajeEjemplar(idEjem).isEmpty())) {

								System.out.println("Ese ejemplar no tiene mensajes asociados");
							} else {
								mensajesAsociados = Controlador.getServicios().getServiciosMensaje().filtrarMensajeEjemplar(idEjem);
								System.out.println("Mensajes asociados encontrados: " + mensajesAsociados.size());
								for (Mensaje m : mensajesAsociados) {
									System.out.println("Autor id y nombre " + m.getIdPersona() + "  "
											+ Controlador.getServicios().getServiciosPersona()
													.obtenerNombreUsuario(m.getIdPersona())
											+ "\tFecha y hora: " + m.getFechaHora().format(formatter));
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else
						System.out.println("Codigo no valido");
				
				}while(opc!=9999);
				}else {System.out.println("No se han encontrado ejemplares");}
				
				
				 in.nextLine();
				break;

			case 4:
				String idEjemStr2="";
				String codigoEjemplar;
				List<Ejemplar> ejemplares2 = new ArrayList<Ejemplar>();
				int k = 1;
				int indiceCodEj = 0;
				if (Controlador.getServicios().getServiciosEjemplar().findAll() != null
						&& !Controlador.getServicios().getServiciosEjemplar().findAll().isEmpty()) {

					ejemplares2 = Controlador.getServicios().getServiciosEjemplar().findAll();
					for (Ejemplar c : ejemplares2) {
						System.out.println(k + ".  id: " + c.getId() + "  nombre: " + c.getNombre());
						k++;
					}
do {
					System.out.println("\nDame un indice (NUMERO DE LISTA) para ver mensajes asociados a ese ejemplar: (9999 para salir)");
				;
				codigoEjemplar = in.next().trim();
if(codigoEjemplar.equals("9999")) {
	break;
}
				try {
					indiceCodEj=Integer.parseInt(codigoEjemplar);
					idEjemStr2 = ejemplares2.get(indiceCodEj - 1).getId().toString();
				}catch(Exception e) {
					if(indiceCodEj!=9999) {
						System.out.println("Error: el indice debe de ser un numero y dentro del rango");
						continue;
				}
				}
				try {
				if (Controlador.getServicios().getServiciosEjemplar().existeCodigoEjemplar(idEjemStr2)) {
					
					Long idej = Long.parseLong(codigoEjemplar);
					Mensaje ms = new Mensaje(Controlador.getServicios().getServiciosMensaje().generarIdMensaje(),
							LocalDateTime.now(), Utils.generarmensaje(idpersona), idej, idpersona);
					
					if (Controlador.getServicios().getServiciosMensaje().InsertarMensaje(ms,
							idpersona) == 0) {
						System.out.println("No se ha podido insertar el mensaje");
					} else {
										System.out.println("Mensaje generado y almacenado con éxito: " + ms.getMensaje());
					}
				} else
					System.out.println("Codigo no valido");
				}catch(Exception e) {
					e.getMessage();
					continue;
				}
}while(!codigoEjemplar.equals("9999"));

					}else {System.out.println("No se han encontrado ejemplares");}
				
				
				 in.nextLine();
				break;

			case 5:
				do {

					System.out.println("\n\tSeleccione una opción:\n");
					System.out.println("\t1.  Filtrar por usuario.\n");
					System.out.println("\t2.  Filtrar por tipoplanta.\n");
					System.out.println("\t3.  Filtrar por Fechas.\n");
					System.out.println("\t9999.  Volver.\n");
					opcion = in.next().trim();
					if (!opcion.matches("\\d+")) {  
		                System.out.println("Entrada no válida. Por favor, introduzca solo un número sin espacios.");
		                continue;
		            }
					try {

						opcionInt = Integer.parseInt(opcion.trim());

					} catch (NumberFormatException e) {

						opcionInt = -1;
					}

					if (opcionInt == 9999) {
						System.out.println("Volviendo...");
						
						
						break;
					} else if (opcionInt < 1 || opcionInt > 3) {
						System.out.println("Opción incorrecta.");
						continue;
					}

					switch (opcionInt) {
					
					case 1:
						List<Persona> users =new ArrayList<Persona>();
						List<Mensaje> mensajespersona=new ArrayList<Mensaje>();
						if (Controlador.getServicios().getServiciosPersona().Find()!= null
						&& !Controlador.getServicios().getServiciosPersona().Find().isEmpty()) {
						users = Controlador.getServicios().getServiciosPersona().Find();
						
						int l = 0;

						for (Persona u : users) {

							System.out.println(l + ": " + u.getId() + "\t" + u.getNombre() + "\t" + u.getEmail());
							l++;
						}
						} else {
								System.out.println("No se ha podido mostrar el listado o la lista está vacía.");
								break;
						 }
						System.out.println("Dame el numero (indice) de usuario sobre el que quiere ver mensajes:");
						String indice = in.next().trim();
						try {
							int indP = Integer.parseInt(indice);

							if (!Controlador.getServicios().getServiciosPersona()
									.existeUsuario(users.get(indP).getId().toString())) {

								System.out.println("No existe persona con ese codigo");

							} else {
								
								if(Controlador.getServicios().getServiciosMensaje().filtrarMensajePersona(users.get(indP).getId())==null && Controlador.getServicios().getServiciosMensaje().filtrarMensajePersona(users.get(indP).getId()).isEmpty()) {
									System.out.println("No se ha podido mostrar el listado o la lista está vacía.");
								}else {
									mensajespersona=	Controlador.getServicios().getServiciosMensaje().filtrarMensajePersona(users.get(indP).getId());
									System.out.println("Mensajes del usuario con id " + users.get(indP).getId());
									for (Mensaje mns : mensajespersona) {
										System.out.println(mns);
								}
							}}
						} catch (NumberFormatException e) {
							System.out.println("No se ha podido obtener los mensjaes. Id introuducido no valido" + e.getMessage());
						} catch (Exception e) {
							System.out.println("Solo se pueden elegir dentro del indice");

						}
						 in.nextLine();
						break;
						
						
						
						
					case 2:
						String cod2 = null;
						int h = 1;
						List<Mensaje> mensajesplanta =new ArrayList <Mensaje>();
						List<Planta> plantas4 = new ArrayList <Planta>();
						if (Controlador.getServicios().getServiciosPlanta().findAll() != null
								&& !Controlador.getServicios().getServiciosPlanta().findAll().isEmpty()) {

					

							System.out.println("   "+Utils.obtenerEncabezado());
							 plantas4= Controlador.getServicios().getServiciosPlanta().findAll();
							for (Planta pl : plantas4) {
								System.out.println(h+"   "+pl);
								h++;
							}
							
						} else {
							System.out.println("No se ha podido mostrar el listado o la lista está vacía.");
							break;
						}
						
						System.out.println("Dame el numero (indice) de la planta sobre el que quieres ver mensajes");
						String index = in.next().trim();
						try {
							int indce = Integer.parseInt(index);

							if (Controlador.getServicios().getServiciosPlanta().existeCodigoPlanta(plantas4.get(indce - 1).getCodigo())) {

						 if(Controlador.getServicios().getServiciosMensaje().filtrarMensajeTipoPlanta(plantas4.get(indce - 1).getCodigo())!=null && !Controlador.getServicios().getServiciosMensaje().filtrarMensajeTipoPlanta(plantas4.get(indce - 1).getCodigo()).isEmpty()) {
							 mensajesplanta= Controlador.getServicios().getServiciosMensaje().filtrarMensajeTipoPlanta(plantas4.get(indce - 1).getCodigo());
							 System.out.println("Mensades de la planta con id " + plantas4.get(indce - 1).getCodigo());
								for (Mensaje msjs : mensajesplanta) {
									System.out.println(msjs);
								}
							}
							}else {
								System.out.println("No se a podido mostras orque la lista esta vacia");
							}
							
						} catch (NumberFormatException e) {
							System.out.println(
									"No ha sido posible realizar la operacion, el indice debe de ser un numero");
							break;

						} catch (Exception s) {
							System.out.println("No se ha podido realizar la operacion, revise datos introducidos");

						}
						
						break;
						
						
						
						
						
					case 3:
						List<Mensaje> mensajesfecha=new ArrayList<Mensaje>();
						LocalDate fechaInicio = null;
						LocalDate fechaFin = null;
						LocalDate fechaActual = LocalDate.now();
						String fechaInicioStr="",fechaFinStr="";
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
do {
						try {
							System.out.println("Introduce la fecha de inicio (formato yyyy-MM-dd)  999 para salir:");
							 fechaInicioStr = in.next().trim();
							if (fechaInicioStr.equals("999")){
								break;
							}
							fechaInicio = LocalDate.parse(fechaInicioStr, formatter);

							System.out.println("Introduce la fecha de fin (formato yyyy-MM-dd): 999 para salir");
							 fechaFinStr = in.next().trim();
							if (fechaInicioStr.equals("999")){
								break;
							}
							fechaFin = LocalDate.parse(fechaFinStr, formatter);

							if (Controlador.getServicios().getServiciosMensaje().verificarfecha(fechaInicio, fechaFin,fechaActual)==-1) {
								
								 System.out.println("La fecha de inicio debe ser anterior o igual a la fecha de fin. Intenta de nuevo.");
							}if(Controlador.getServicios().getServiciosMensaje().verificarfecha(fechaInicio, fechaFin,fechaActual)==-2){
								
								 System.out.println("La fecha de inicio no puede ser posterior al día actual. Intenta de nuevo.");
							}if(Controlador.getServicios().getServiciosMensaje().verificarfecha(fechaInicio, fechaFin,fechaActual)==1) {
								 if(Controlador.getServicios().getServiciosMensaje().filtrarMensajeRangoFechas(fechaInicio, fechaFin)!=null
										 && !Controlador.getServicios().getServiciosMensaje().filtrarMensajeRangoFechas(fechaInicio, fechaFin).isEmpty()) {
								mensajesfecha=Controlador.getServicios().getServiciosMensaje().filtrarMensajeRangoFechas(fechaInicio, fechaFin);
								System.out.println("Rango de fechas elegido: " + fechaInicio + " a " + fechaFin);
								for (Mensaje ms : mensajesfecha) {
									System.out.println(ms);
								}
							
						}else {
							System.out.println("No hay mensajes para el rango de fechas elegido");
						}
							}	 
							} catch (NumberFormatException e) {
							System.out.println("Error al convertir el id de usuario a número: " + e.getMessage());
						} catch (DateTimeParseException e) {
							System.out.println("Formato de fecha no válido. Debes de usar el formato yyyy-MM-dd.");

						}
}while(!fechaInicioStr.equals("999")||!fechaFinStr.equals("999"));
in.nextLine();
						break;

					default:
						System.out.println("Opción incorrecta.");
						break;
					}
in.reset();
				} while (opcionInt != 9999);
				break;

			case 99:
				break;
			default:
				System.out.println("Opción incorrecta.");
				break;

			}
			 in.nextLine();
		} while (opcionInt != 99);
	}

}
