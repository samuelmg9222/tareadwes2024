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

public class PlantaDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	 
	    public PlantaDAO(Connection c) {
	        this.con = c;
	    }

	   public void insertarPlanta() {
		   
		   try {
			if( this.con ==null ||this.con.isClosed()) 
				   this.con=ConexionBD.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        Scanner in = new Scanner(System.in);

	       
	        System.out.println("Dame codigo de una nueva planta");
	        String codigo = in.nextLine().trim().toUpperCase();
	        
	        System.out.println("Dame nombre comun de una planta");
	        String nombreComun = in.nextLine();
	        
	        System.out.println("Dame nombre cientifico de una planta");
	        String nombreCientifico = in.nextLine();

	        
	        Planta nueva = new Planta(codigo, nombreComun, nombreCientifico);

	      
	        try {
	            ps = con.prepareStatement("INSERT INTO plantas(codigo, nombrecomun, nombrecientifico) VALUES(?, ?, ?)");
	            
	            ps.setString(1, nueva.getCodigo());
	            ps.setString(2, nueva.getNombrecomun());
	            ps.setString(3, nueva.getNombrecientifico());

	            ps.executeUpdate();
	            ps.close();
	            ConexionBD.cerrarConexion();
	            System.out.println("Planta insertada correctamente");
	        } catch (SQLException e) {
	            System.out.println("Se ha producido una SQLException: " + e.getMessage());  e.printStackTrace();
	        }

	        
	        
	    }
	   public void verplantas() {
		   try {
				if( this.con ==null ||this.con.isClosed()) 
					   this.con=ConexionBD.getInstance().getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        List<Planta> plantas = new ArrayList<>();
	        String sql = "SELECT * FROM PLANTAS";

	        try {
	             ps = con.prepareStatement(sql);
	             rs = ps.executeQuery();

	            while (rs.next()) {
	                String codigo = rs.getString("codigo");
	                String nombreComun = rs.getString("nombrecomun");
	                String nombreCientifico = rs.getString("nombrecientifico");

	                Planta planta = new Planta(codigo, nombreComun, nombreCientifico);
	                plantas.add(planta);
	               
	            } 
	            for(Planta pl:plantas) {
	                	System.out.println(pl); 
	                }
	            ConexionBD.cerrarConexion();
	            ps.close();
	            rs.close();
	        } catch (SQLException e) {
	            System.err.println("Error: " + e.getMessage());
	            e.printStackTrace();
	        }
	    
	   }
	   /*
	   int insertar(Planta p);
		
			int modificar(Planta p);
		
			int eliminar(Planta p);
			
			Planta findById(int id);
			ArrayList<Planta> findByNombre(String nombre);
			List<Planta> findAll();
		}
		*/
}
	