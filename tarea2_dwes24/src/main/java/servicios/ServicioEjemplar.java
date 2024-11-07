package servicios;

import java.util.ArrayList;
import java.util.List;

import controlador.Controlador;
import dao.EjemplarDAO;
import dao.PlantaDAO;
import modelo.Ejemplar;
import modelo.Planta;
import utilidades.ConexionBD;

public class ServicioEjemplar {
	private ConexionBD con;
	private EjemplarDAO ejemplarDAO;
public ServicioEjemplar() {
	con=ConexionBD.getInstance();
	ejemplarDAO=(EjemplarDAO) con.getEjemplarDAO();
}

public int InsertarEjemplar(Ejemplar ej,String codplanta) {
	return ejemplarDAO.nuevoEjemplar(ej, codplanta);
}

public boolean verificarInsercionEjemplar(String s){
	  if (Controlador.getServicios().getServiciosPlanta().existeCodigoPlanta(s)) {
		    
	        return false;
	    }
	  
	  
	  return true;
}



public Long generarIdEjemplar() {
	return ejemplarDAO.generarIdEjemplar();
}

public String generarNombreEjemplar(String cd) {
	return ejemplarDAO.generarNombreEjemplar(cd);
}



public List<Ejemplar> obtenerEjemplaresPorCodigo(String codigo) {
    return ejemplarDAO.obtenerEjemplaresPorCodigoPlanta(codigo);
}

public boolean procesarCodigo(String codigo,ArrayList<String> cods) {
	
    if (cods.contains(codigo)) {
        System.out.println("Ese código ya lo has introducido.");
        return false;
    } else if (Controlador.getServicios().getServiciosPlanta().existeCodigoPlanta(codigo)) {
    	cods.add(codigo);
      
     
    	 
    }else {
    	System.out.println ("Ese código de planta no existe.");
    	return false;
}
	return true;
    
}


public List<Ejemplar> findAll() {
    return ejemplarDAO.verEjemplares();
}

    public boolean existeCodigoEjemplar(String p) {
        if (!ejemplarDAO.existeCodigoEjemplar(p)) {
            
            return false;
        
    }
    	return true;

    }
	

}