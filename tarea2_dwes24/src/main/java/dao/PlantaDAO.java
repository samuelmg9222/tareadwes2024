package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;



import modelo.Planta;
import utilidades.ConexionBD;
import utilidades.Utils;

public class PlantaDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	    public PlantaDAO(Connection c) {
	        this.con = c;
	    }
	    
	   public int insertarPlanta(Planta p) {
		   int exito=0;

	        try {
	 			if( this.con == null ||this.con.isClosed()) 
					   this.con=ConexionBD.getInstance().getConnection();
			
	            ps = con.prepareStatement("INSERT INTO plantas(codigo, nombrecomun, nombrecientifico) VALUES(?, ?, ?)");
	            
	            ps.setString(1, p.getCodigo());
	            ps.setString(2, p.getNombrecomun());
	            ps.setString(3, p.getNombrecientifico());

	           exito =ps.executeUpdate();
	            ps.close();
	            ConexionBD.cerrarConexion();
	            
	        } catch (SQLException e) {
	            System.out.println("Se ha producido una SQLException: " + e.getMessage());  e.printStackTrace();
	        }
			return exito;
			

	    }
	   public  boolean existeCodigoPlanta(String codigo) {
		    boolean existe = false;
			try {
				if( this.con ==null ||this.con.isClosed()) 
					   this.con=ConexionBD.getInstance().getConnection();
			
				
			
		    String consulta = "SELECT COUNT(*) FROM plantas WHERE codigo = ?";


		    
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
	   public Planta obtenerdatosplanta(String cod){
		   String nombreComun ="",nombreCientifico="";
		   Planta planta = null;
		   
		   try {
				if( this.con ==null ||this.con.isClosed()) 
					   this.con=ConexionBD.getInstance().getConnection();
			
	        
	        String sql = "SELECT nombrecientifico , nombrecomun FROM PLANTAS WHERE CODIGO =?";

	        
	             ps = con.prepareStatement(sql);
	             ps.setString(1, cod);
	             rs = ps.executeQuery();
	            
	            while (rs.next()) {
	                 nombreComun = rs.getString("nombrecomun");
	                 nombreCientifico = rs.getString("nombrecientifico");
	               
	            } 
	             planta = new Planta(cod, nombreComun, nombreCientifico);
	            ConexionBD.cerrarConexion();
	            ps.close();
	            rs.close();
	        } catch (SQLException e) {
	            System.err.println("Error: " + e.getMessage());
	            e.printStackTrace();
	        }
		return planta;
		   
		   
		   
	   }
	   public int  modificarplantas(Planta p){
	   
		   try {
	 			if( this.con == null ||this.con.isClosed()) 
					   this.con=ConexionBD.getInstance().getConnection();
	 			String sql=("UPDATE PLANTAS SET NOMBRECIENTIFICO=? , NOMBRECOMUN=? WHERE CODIGO =?");
	 			 ps = con.prepareStatement(sql);
	             
	            ps.setString(1, p.getNombrecientifico());
	            ps.setString(2, p.getNombrecomun());
	            ps.setString(3, p.getCodigo());

	        return ps.executeUpdate();
	            

	          
	            
	        } catch (SQLException e) {
	            System.out.println("Se ha producido una SQLException: " + e.getMessage());  e.printStackTrace();
	        }
		return 0;
	   }
	   
	   public List<Planta> findAll(){
		   List<Planta> plantas = new ArrayList<>();
		   try {
				if( this.con ==null ||this.con.isClosed()) 
					   this.con=ConexionBD.getInstance().getConnection();
			
	        
	        String sql = "SELECT * FROM PLANTAS ORDER BY nombreComun ASC";

	       
	             ps = con.prepareStatement(sql);
	             rs = ps.executeQuery();

	            while (rs.next()) {
	                String codigo = rs.getString("codigo");
	                String nombreComun = rs.getString("nombrecomun");
	                String nombreCientifico = rs.getString("nombrecientifico");

	                Planta planta = new Planta(codigo, nombreComun, nombreCientifico);
	                plantas.add(planta);
	               
	            } 
	          
	            ConexionBD.cerrarConexion();
	            ps.close();
	            rs.close();
	        } catch (SQLException e) {
	            System.err.println("Error: " + e.getMessage());
	            e.printStackTrace();
	        }
	       
	    return plantas;
	   }
	 

	

	
}
	