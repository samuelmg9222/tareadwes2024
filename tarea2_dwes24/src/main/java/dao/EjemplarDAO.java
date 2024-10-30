package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.mysql.cj.xdevapi.Statement;

import modelo.Ejemplar;
import modelo.Planta;
import principal.ConexionBD;
import utilidades.Utils;

public class EjemplarDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public EjemplarDAO(Connection c) {
		this.con = c;
	}

	public void nuevoEjemplar() {

		String codigoplanta;
		String nombre;
		Scanner in = new Scanner(System.in);
		boolean existe = false;
		Utils utils = new Utils(con);
	      do {
	            System.out.println("Dame código de una planta:");
	            codigoplanta = in.next().trim();
	            existe = utils.existeCodigoPlanta(codigoplanta);

	            if (!existe) {
	                System.out.println("El código no existe. Por favor, ingrese un código valido.");
	            } 
	        } while (!existe);
	    
	
		
		long id = utils.generarIdEjemplar();
		String nombregenerado = utils.generarNombreEjemplar(codigoplanta);
		nombre = id + "_" + nombregenerado;
		Ejemplar nuevo = new Ejemplar(id, nombre);
		String sqlIns = "INSERT INTO ejemplares(id, nombre, codigoplanta) VALUES(?, ?, ?)";
		try {
			if (this.con == null || this.con.isClosed())
				this.con = ConexionBD.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try (PreparedStatement ps = con.prepareStatement(sqlIns)) {

			ps.setLong(1, nuevo.getId());
			ps.setString(2, nuevo.getNombre());
			ps.setString(3, codigoplanta);

			ps.executeUpdate();
			ps.close();
			ConexionBD.cerrarConexion();
			System.out.println("Planta insertada correctamente");

		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException: " + e.getMessage());
			e.printStackTrace();
		}

	}

	
	
	
	public void filtrarEjemplares() {
		String codigo;
		Scanner in = new Scanner(System.in);
		Set<String> codigos = new HashSet<String>();
		do {

			System.out.println("Dame codigo de una planta para ver sus ejemplares: (INTRODUCE 0011 para salir)");
			codigo = in.next().trim();
			if (codigo.equals("0011")) {
				System.out.println("Saliendo...");
				break;
			} 
			if (codigos.contains(codigo)) {
				System.out.println("Lo siento, ese codigo ya lo has introducido:");
			} else {
				String sql = "SELECT * FROM PLANtaS WHERE CODIGO = ?";

				try {
					if (this.con == null || this.con.isClosed())
						this.con = ConexionBD.getInstance().getConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				

				try (PreparedStatement ps = con.prepareStatement(sql)) {
					ps.setString(1, codigo);
					try (ResultSet rs = ps.executeQuery()) {
						if (rs.next()) {
							
							codigos.add(codigo);
							System.out.println("codigo valido,desea alguno mas?(S/N)");
							String opc;
							do {
							 opc=in.next().toUpperCase().trim();
							if(!opc.equals("S") && !opc.equals("N")) {
								System.out.println("Introduce una opcion valida; (S/N)");
							}
							}while(!opc.equals("S") &&!opc.equals("N"));
							if(opc.equals("N")) {
								break;
							}
						} else {
							System.out.println("Ese codigo de planta no existe, lo siento.");
						}

					}
				} catch (SQLException e) {
					System.err.println("Error al consultar plantas: " + e.getMessage());
					e.printStackTrace();
				}

			}

		} while (!codigo.equals("0011"));

		
		if(!codigos.isEmpty()) {
			List<Ejemplar> ejemplares = new ArrayList<>();
			Ejemplar ejemplar = null;
			for(String c:codigos) {
				String sql = "SELECT * FROM EJEMPLARES WHERE codigoplanta = ?";

				  try (PreparedStatement ps = con.prepareStatement(sql)) {
	                    ps.setString(1, c);
	                    try (ResultSet rs = ps.executeQuery()) {
	                        while (rs.next()) {
	                            Long id = rs.getLong("id");
	                            String nombre = rs.getString("nombre");
	                            
	                             ejemplar = new Ejemplar(id, nombre);
	                            ejemplares.add(ejemplar);
	                           

	                        }
	                        System.out.println("Ejemplares con codigo de planta "+c);
	                        for (Ejemplar pl : ejemplares) {
        	                    System.out.println(pl);
        	                }
                            ejemplares.remove(ejemplar);
	                    }catch (SQLException e) {
	    					System.err.println("Error al consultar plantas: " + e.getMessage());
	    					e.printStackTrace();
	    				}
	                

	                
	                
				} catch (SQLException e) {
					System.err.println("Error al consultar plantas: " + e.getMessage());
					e.printStackTrace();
				}

			}

			}
			
			
		}
	}

