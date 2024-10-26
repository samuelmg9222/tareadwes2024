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
	 private ConexionBD conexionBD;
	   public PlantaDAO() {
	        this.conexionBD = new ConexionBD();
	    }

	   public void insertarPlanta() {
	        Scanner in = new Scanner(System.in);

	       
	        System.out.println("Dame codigo de una nueva planta");
	        String codigo = in.nextLine().trim().toUpperCase();
	        
	        System.out.println("Dame nombre comun de una planta");
	        String nombreComun = in.nextLine();
	        
	        System.out.println("Dame nombre cientifico de una planta");
	        String nombreCientifico = in.nextLine();

	        
	        Planta nueva = new Planta(codigo, nombreComun, nombreCientifico);

	      
	        try (Connection con = conexionBD.getConnection();
	             PreparedStatement ps = con.prepareStatement("INSERT INTO plantas(codigo, nombrecomun, nombrecientifico) VALUES(?, ?, ?)")) {
	            
	            ps.setString(1, nueva.getCodigo());
	            ps.setString(2, nueva.getNombrecomun());
	            ps.setString(3, nueva.getNombrecientifico());

	            ps.executeUpdate();
	            System.out.println("Planta insertada correctamente");
	        } catch (SQLException e) {
	            System.out.println("Se ha producido una SQLException: " + e.getMessage());
	        }

	        
	        in.close();
	    }
	   public void verplantas() {
	        List<Planta> plantas = new ArrayList<>();
	        String sql = "SELECT * FROM PLANTAS";

	        try (Connection con = conexionBD.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {
	                String codigo = rs.getString("codigo");
	                String nombreComun = rs.getString("nombrecomun");
	                String nombreCientifico = rs.getString("nombrecientifico");

	                Planta planta = new Planta(codigo, nombreComun, nombreCientifico);
	                plantas.add(planta);
	                for(Planta p:plantas) {
	                	System.out.println(p);
	                }
	            }
	        } catch (SQLException e) {
	            System.err.println("Error al consultar plantas: " + e.getMessage());
	        }
	    
	   }
	   
}
	