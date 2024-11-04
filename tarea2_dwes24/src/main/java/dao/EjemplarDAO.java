package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
	
	public void nuevoEjemplar(Long a,String b,String c) {


		String sqlIns = "INSERT INTO ejemplares(id, nombre, codigoplanta) VALUES(?, ?, ?)";
		try {
			if (this.con == null || this.con.isClosed())
				this.con = ConexionBD.getInstance().getConnection();
		
		
		PreparedStatement ps = con.prepareStatement(sqlIns);

			ps.setLong(1, a);
			ps.setString(2, b);
			ps.setString(3, c);
			
			ps.executeUpdate();
			ps.close();
			
					 System.out.println("Inserción en la tabla `ejemplares` completada con éxito.");
				ConexionBD.cerrarConexion();
			

		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException: " + e.getMessage());
			e.printStackTrace();
		}
		
			

	

	}

	
	public  boolean existeCodigoEjemplar(String codigo) {
		try {
			if( this.con ==null ||this.con.isClosed()) 
				   this.con=ConexionBD.getInstance().getConnection();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	    String consulta = "SELECT COUNT(*) FROM ejemplares WHERE id = ?";
	    boolean existe = false;

	    try  {
	    	 ps = con.prepareStatement(consulta);
	        ps.setString(1, codigo);

	        rs = ps.executeQuery();
	            if (rs.next() && rs.getInt(1) > 0) {
	                existe = true;
	                ps.close();
		            ConexionBD.cerrarConexion();
	            }
	        

	    } catch (SQLException e) {
	        System.err.println("Error al consultar si el código ya existe: " + e.getMessage());
	    }

	    return existe;
	}
	
	public void filtrarEjemplares() {
		String codigo = null;
		Scanner in = new Scanner(System.in);
		Set<String> codigos = new HashSet<String>();
		
				String sql = "SELECT * FROM PLANtaS WHERE CODIGO = ?";

				try {
					if (this.con == null || this.con.isClosed())
						this.con = ConexionBD.getInstance().getConnection();
				
				

				PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, codigo);
					ResultSet rs = ps.executeQuery();
						if (rs.next()) {
							
							codigos.add(codigo);
							
						}
					
				} catch (SQLException e) {
					System.err.println("Error al consultar plantas: " + e.getMessage());
					e.printStackTrace();
				}

			

		

		
		if(!codigos.isEmpty()) {
			List<Ejemplar> ejemplares = new ArrayList<>();
			Ejemplar ejemplar = null;
			for(String c:codigos) {
				String sql2 = "SELECT * FROM EJEMPLARES WHERE codigoplanta = ?";

				  try (PreparedStatement ps = con.prepareStatement(sql2)) {
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
	 public List<Ejemplar> obtenerEjemplaresPorCodigoPlanta(String codigoPlanta) {
	        List<Ejemplar> ejemplares = new ArrayList<>();
	        String sql = "SELECT * FROM EJEMPLARES WHERE CODIGOPLANTA = ?";
	        try {
				if (this.con == null || this.con.isClosed())
					this.con = ConexionBD.getInstance().getConnection();
	          ps = con.prepareStatement(sql);
	            ps.setString(1, codigoPlanta);
	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                    Long id = rs.getLong("id");
	                    String nombre = rs.getString("nombre");
	                    Ejemplar ejemplar = new Ejemplar(id, nombre);
	                    ejemplares.add(ejemplar);
	                }
	                ps.close();
	    			
					
				ConexionBD.cerrarConexion();
	            }
	        }catch (SQLException e) {
				System.err.println("Error al consultar plantas: " + e.getMessage());
				e.printStackTrace();
			}
			return ejemplares;


	    }
	 
	 public List<Ejemplar> verEjemplares() {
		 List<Ejemplar> ejemplares = new ArrayList<>();
		 
		 String sql = "SELECT * FROM ejemplares";
		 try {
				if (this.con == null || this.con.isClosed())
					this.con = ConexionBD.getInstance().getConnection();
	          ps = con.prepareStatement(sql);
	            
	            rs = ps.executeQuery();
	                while (rs.next()) {
	                    Long id = rs.getLong("id");
	                    String nombre = rs.getString("nombre");
	               
	                    Ejemplar ejemplar = new Ejemplar(id, nombre);
	                    ejemplares.add(ejemplar);
	                }
	                ps.close();
	    			rs.close();
					
				ConexionBD.cerrarConexion();
	            
	        }catch (SQLException e) {
				System.err.println("Error al consultar plantas: " + e.getMessage());
				
			}
		return ejemplares;
		 
		 
		 


		 
	 
	}
	 public String generarNombreEjemplar(String codigo) {
			String nombreEjemplar = null;
			 try {
					if( this.con ==null ||this.con.isClosed()) 
						   this.con=ConexionBD.getInstance().getConnection();
				

			
			  String sql ="SELECT nombrecomun FROM PLANTAS WHERE CODIGO=?";
			  
				  ps = con.prepareStatement(sql);
			  ps.setString(1, codigo); 
		      rs = ps.executeQuery();
		      
		    if (rs.next()) {
		    	nombreEjemplar = rs.getString("nombrecomun");
		    }
		    
		} catch (SQLException e) {
		    System.err.println("Error al generar un nuevo ID: " + e.getMessage());
		    e.printStackTrace();
		}
			    
			return nombreEjemplar;
			
		}
	 
	 public long generarIdEjemplar() {
			long id = 0;
			 try {
					if( this.con ==null ||this.con.isClosed()) 
						   this.con=ConexionBD.getInstance().getConnection();
				
		    
		    String sql = "SELECT COALESCE(MAX(id), 0) + 1 AS nuevo_id FROM ejemplares";
		    
		    PreparedStatement ps = con.prepareStatement(sql);
		         ResultSet rs = ps.executeQuery();
		        
		       
		        if (rs.next()) {
		            id = rs.getLong("nuevo_id");
		        }
		    } catch (SQLException e) {
		        System.err.println("Error al generar un nuevo ID: " + e.getMessage());
		        e.printStackTrace();
		  
		    }
		    return id;
		}
}
