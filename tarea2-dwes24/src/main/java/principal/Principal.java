package principal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.mysql.cj.jdbc.MysqlDataSource;

import modelo.Planta;

public class Principal {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Dame codigo de una nueva planta");
		String codigo = in.nextLine().trim().toUpperCase();
		System.out.println("Dame nombre comun de una planta");
		String nombre_comun = in.nextLine();
		System.out.println("Dame nombre cientifico de una planta");
		String nombre_cientifico = in.nextLine();

		Planta nueva = new Planta(codigo, nombre_comun, nombre_cientifico);

		Connection con;
		MysqlDataSource m = new MysqlDataSource();
		FileInputStream fis;
		Properties prop = null;

		   String url = null;
	        String usuario = null;
	        String password = null;

		
		
		try {
			con = DriverManager.getConnection(url, usuario, password);
			fis = new FileInputStream("src/main/resources/db.properties");
			prop.load(fis);
			url = prop.getProperty("url");
			usuario = prop.getProperty("usuario");
			password = prop.getProperty("password");

			m.setUrl(url);
			m.setUser(usuario);
			m.setPassword(password);

			con = m.getConnection();
/*
			String sql = "INSERT INTO plantas(codigo, nombrecomun, nombrecientifico) VALUES ('" + nueva.getCodigo()
					+ "', '" + nueva.getNombrecomun() + "', '" + nueva.getNombrecientifico() + "')";
Otra opcion
		*/	
			String sql2="INSERT INTO plantas(codigo, nombrecomun, nombrecientifico) VALUES(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql2);
			ps.setString(1,nueva.getCodigo());
			ps.setString(1,nueva.getNombrecomun());
			ps.setString(1,nueva.getNombrecientifico());
			ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Se ha producido una qlexceptio " + e.getMessage());

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
