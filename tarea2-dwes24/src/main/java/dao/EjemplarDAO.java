package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import modelo.Ejemplar;
import modelo.Planta;
import principal.ConexionBD;

public class EjemplarDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	 
	    public EjemplarDAO(Connection c) {
	        this.con = c;
	    }
	    
	   public void nuevoEjemplar() {
		   
		   try {
				if( this.con ==null ||this.con.isClosed()) 
					   this.con=ConexionBD.getInstance().getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   String codigoplanta;
		   String nombre;
		   Scanner in = new Scanner(System.in);
		   String sql = "SELECT * FROM PLANTAS WHERE id = ?";
		   String sqlInsertar = "INSERT INTO EJEMPLARES(id,nombre,codigoplanta)";
		    boolean encontrado = false;
		    do{
		        System.out.println("Dame código de una planta:");
		         codigoplanta = in.next().toUpperCase().trim();
	        
	      
	        try {
	             ps = con.prepareStatement(sql);
	             rs = ps.executeQuery();

	            while (rs.next()) {
	                 codigoplanta = rs.getString("codigo"); 
	            } 
	            ConexionBD.cerrarConexion();
	            ps.close();
	            rs.close();
	            encontrado=true;
	        } catch (SQLException e) {
	            System.err.println("Error al consultar plantas: " + e.getMessage());
	            e.printStackTrace();
	        }
	       
		   
		   
		   
	   }while(encontrado=false);
		   nombre=in.nextLine().trim().toUpperCase();
		    try {
		    	long id = 0;
	            ps = con.prepareStatement("INSERT INTO ejemplares(id, nombre, codigoplanta) VALUES(?, ?, ?)");
	            Ejemplar nuevo = new Ejemplar(id, nombre);
	            ps.setLong(1, nuevo.getId());
	            ps.setString(2, nuevo.getNombre());
	            ps.setString(3, codigoplanta);

	            ps.executeUpdate();
	            ps.close();
	            ConexionBD.cerrarConexion();
	            System.out.println("Planta insertada correctamente");
	        } catch (SQLException e) {
	            System.out.println("Se ha producido una SQLException: " + e.getMessage());  e.printStackTrace();
	        }

	    
	    
	    
	    
	    
	    
	    
	    
}}
 