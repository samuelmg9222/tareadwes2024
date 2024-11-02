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

import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.cj.xdevapi.Statement;

import modelo.Planta;
import principal.ConexionBD;
import utilidades.Utils;

public class PlantaDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	    public PlantaDAO(Connection c) {
	        this.con = c;
	    }
	    
	   public void insertarPlanta(String a, String b, String c) {
		   

	        try {
	 			if( this.con == null ||this.con.isClosed()) 
					   this.con=ConexionBD.getInstance().getConnection();
			
	            ps = con.prepareStatement("INSERT INTO plantas(codigo, nombrecomun, nombrecientifico) VALUES(?, ?, ?)");
	            
	            ps.setString(1, a);
	            ps.setString(2, b);
	            ps.setString(3, c);

	            ps.executeUpdate();
	            ps.close();
	            ConexionBD.cerrarConexion();
	            
	        } catch (SQLException e) {
	            System.out.println("Se ha producido una SQLException: " + e.getMessage());  e.printStackTrace();
	        }

	        
	        
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
	   public void modificarplantas(String cod,String nom, String nomcien){
	   
		   try {
	 			if( this.con == null ||this.con.isClosed()) 
					   this.con=ConexionBD.getInstance().getConnection();
			
	 			 String sql=("UPDATE PLANTAS SET NOMBRECIENTIFICO=? , NOMBRE=? WHERE CODIGO =?");
	 			 ps = con.prepareStatement(sql);
	             rs = ps.executeQuery();
	            ps.setString(1, nomcien);
	            ps.setString(2, nom);
	            ps.setString(3, cod);

	            ps.executeUpdate();
	            ps.close();
	            ConexionBD.cerrarConexion();
	            
	        } catch (SQLException e) {
	            System.out.println("Se ha producido una SQLException: " + e.getMessage());  e.printStackTrace();
	        }
	   }
	   
	   public List<Planta> obtenerPlantas(){
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
	