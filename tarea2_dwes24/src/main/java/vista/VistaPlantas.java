
package vista;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controlador.Controlador;
import modelo.Planta;
import utilidades.ConexionBD;
import utilidades.Utils;

public class VistaPlantas {
	private Connection con;
	private static VistaPlantas portalvistaPlantas;

	public VistaPlantas(Connection con) {
		this.con = con;
	}

	public static VistaPlantas getPortalvistaPlantas() {
		if (portalvistaPlantas == null)
			portalvistaPlantas = new VistaPlantas(ConexionBD.getInstance().getConnection());
		return portalvistaPlantas;
	}

	public void mostrarMenuGestionPlantas() {
		Scanner in = new Scanner(System.in);
		System.out.println("Gestion de plantas");
		String opcion = "";
		int opcionInt = -1;
		do {
			System.out.println("\n\tSeleccione una opción:\n");
			System.out.println("\t1.  Crear nueva Planta (CU4_A).\n");
			System.out.println("\t2.  Ver plantas (CU1).\n");
			System.out.println("\t3.  Modificar planta (CU4_B).\n");
			System.out.println("\t99.  Volver al menú Principal\n");
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
			}

			switch (opcionInt) {
			case 1:

				String nombreComun;
				String codigo;
				String nombreCientifico;
				do {
					System.out.println("Dame código de una nueva planta (FORMATO DE 4 LETRAS SIN TILDES [AAAA]) Introduce 9999 para salir:");
					codigo = in.nextLine().trim().toUpperCase();
					if (codigo.equals("9999")) {
						break;
					}
					System.out.println("Dame nombre común de la planta:");
					nombreComun = in.nextLine().trim().toUpperCase();  

					System.out.println("Dame nombre científico de la planta:");
					nombreCientifico = in.nextLine().trim().toUpperCase(); 

					Planta p = new Planta(codigo, nombreComun, nombreCientifico);
					int operacion = Controlador.getServicios().getServiciosPlanta().verificarPlanta(p);
					if (operacion == 1) {
						try {
							if (Controlador.getServicios().getServiciosPlanta().InsertarPlanta(p) > 0)
								System.out.println(nombreComun + " (" + nombreCientifico + ") ingresada con éxito: código "
										+ codigo + ".");
						} catch (Exception e) {
							System.out.println("Se ha producido una excepción: " + e.getMessage());
						}
					}
					if (operacion == -1)
						System.out.println("Formato de codigo no valido. Solo pueden ser 4 letras sin tildes ni ñ");
					if (operacion == -2)
						System.out.println("No se ha completado la operacion. Ese codigo de planta ya existe para otra planta");
					if (operacion == -3)
						System.out.println("Nombre comun no valido. Debe ser de 3 a 99 letras incluyendo espacios y sin tildes ni ñ");
					if (operacion == -4)
						System.out.println("Nombre cientifico no valido. Debe ser de 3 a 99 letras incluyendo espacios y sin tildes ni ñ");
					if (operacion == 1) {
						System.out.println("Planta registrada con exito");
					}
				} while (codigo.equals("9999"));
				break;

			case 2:
				listarPlantas();
				break;

			case 3:

				codigo = "";
				nombreComun = "";
				nombreCientifico = "";
				List<Planta> plantas2 = new ArrayList<Planta>();
				int ind = 0;
				if (Controlador.getServicios().getServiciosPlanta().findAll() != null
						&& !Controlador.getServicios().getServiciosPlanta().findAll().isEmpty()) {
					plantas2 = Controlador.getServicios().getServiciosPlanta().findAll();

					System.out.println("   " + Utils.obtenerEncabezado());
					int i = 1;

					for (Planta pl : plantas2) {
						System.out.println(i + ": " +pl.getCodigo()+"\t"+ pl.getNombrecomun() + "\t" + pl.getNombrecientifico());
						i++;
					}
				} else {
					System.out.println("No se ha podido mostrar el listado o la lista está vacía.");
					break;
				}
				
					try {
						System.out.println("Dame el número (índice) de la planta que desea modificar: (Introduce 9999 si desea salir)");
						String indice = in.nextLine().trim();  

						ind = Integer.parseInt(indice);
						if (ind == 9999) {
							break;
						}
						System.out.println("Dame nombre común que desea poner a la planta. Si no desea modificarlo, introduce su nombre como está:");
						nombreComun = in.nextLine().trim().toUpperCase();

						System.out.println("Dame nombre científico de la planta. Si no desea modificarlo, introduce su nombre como está:");
						nombreCientifico = in.nextLine().trim().toUpperCase(); 

						Planta pl = new Planta(plantas2.get(ind - 1).getCodigo(), nombreComun, nombreCientifico);

						int resultado = Controlador.getServicios().getServiciosPlanta().verificarModificacion(pl,
								plantas2, ind);

						switch (resultado) {
						case 1:
							Controlador.getServicios().getServiciosPlanta().modificarPlanta(pl);
							System.out.println("Has modificado la planta de código [" + pl.getCodigo() + "] con éxito");
							break;
						case -1:
							System.out.println("Número fuera de rango del índice");
							break;
						case -2:
							System.out.println("Nombre común no válido. Debe ser de 3 a 99 letras incluyendo espacios y sin tildes ni ñ");
							break;
						case -3:
							System.out.println("Nombre científico no válido. Debe ser de 3 a 99 letras incluyendo espacios y sin tildes ni ñ");
							break;
						case -4:
							System.out.println("No se ha podido modificar nada ya que los datos son los mismos que los ya existentes");
							break;
						default:
							System.out.println("Error desconocido al intentar modificar la planta");
						}
					} catch (IndexOutOfBoundsException e) {
						System.out.println("El índice ingresado está fuera del rango de la lista de plantas.");
					} catch (NumberFormatException e) {
						System.out.println("El valor ingresado no es un número válido.");
					} catch (Exception e) {
						System.out.println("No se ha podido realizar la modificación.");
					}
				
				break;

			case 99:
				break;
			default:
				System.out.println("Opción no válida.");
				break;
			}
		} while (opcionInt != 99);
	}

	public List<Planta> listarPlantas() {
		if (Controlador.getServicios().getServiciosPlanta().findAll() != null
				&& !Controlador.getServicios().getServiciosPlanta().findAll().isEmpty()) {

			List<Planta> plantas = Controlador.getServicios().getServiciosPlanta().findAll();

			System.out.println(Utils.obtenerEncabezado());
			for (Planta pl : plantas) {
				System.out.println(pl);
			}
			return plantas;
		} else {
			System.out.println("No se ha podido mostrar el listado o la lista está vacía.");
			return new ArrayList<>();
		}
	}
}
