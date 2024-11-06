package servicios;

import java.util.ArrayList;
import java.util.List;

import dao.EjemplarDAO;
import dao.PlantaDAO;
import modelo.Ejemplar;
import modelo.Planta;
import principal.ConexionBD;
import principal.Controlador;

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

public void verificarentradaFiltrarPorCodPlanta(String codigo) {
	 while (true) {
if (codigo.equals("9999")) {
    
    
    break;
}

procesarCodigo(codigo);
	 }
}

public void procesarCodigo(String codigo) {
	ArrayList <String> cods =new ArrayList<String>();
    if (cods.contains(codigo)) {
        System.out.println("Ese código ya lo has introducido.");
    } else if (Controlador.getServicios().getServiciosPlanta().existeCodigoPlanta(codigo)) {
    	cods.add(codigo);
      
     
    	 
    }else {
    	System.out.println ("Ese código de planta no existe.");
}
    
}


public List<Ejemplar> findAll() {
    return ejemplarDAO.verEjemplares();
}

    public boolean existeCodigoEjemplar(String p) {
        if (ejemplarDAO.existeCodigoEjemplar(p)) {
            
            return false;
        
    }
    	return true;

    }
	

}