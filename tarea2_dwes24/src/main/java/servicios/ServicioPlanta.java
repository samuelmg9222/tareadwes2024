package servicios;

import java.util.List;

import dao.PlantaDAO;
import modelo.Planta;
import principal.ConexionBD;

public class ServicioPlanta {

	
	private ConexionBD con;
		private PlantaDAO plantaDAO;
	public ServicioPlanta() {
		con=ConexionBD.getInstance();
		plantaDAO=(PlantaDAO) con.getPlantaDAO();
	}
	
	
	public boolean verificarPlanta(Planta p) {
	   
	    if (!p.getCodigo().matches("[A-Z]{4}")) {
	        
	        return false;
	    }
	    if (plantaDAO.existeCodigoPlanta(p.getCodigo())) {
	        
	        return false;
	    }


	    if (!p.getNombrecomun().matches("[A-Z][a-zA-Z\\s]{2,99}")) {
	        
	        return false;
	    }

	    if (!p.getNombrecientifico().matches("[A-Z][a-zA-Z\\s]{2,99}")) {
	        
	        return false;
	    }
	    return true;
	}
	
	
	public int InsertarPlanta(Planta p) {
		return plantaDAO.insertarPlanta(p);
	}
	

	public List<Planta> findAll() {
        return plantaDAO.findAll();

}
public boolean existeCodigoPlanta(String p) {
    if (plantaDAO.existeCodigoPlanta(p)) {
        
        return true;
    
}
	return false;

}
public int modificarPlanta(Planta p) {
	return plantaDAO.modificarplantas(p);
	
}



public Planta obtenerdatosplanta(String codigo) {
	return plantaDAO.obtenerdatosplanta(codigo);
	
}



	public boolean verificarModificacion(Planta p, List<Planta> plantas2, int ind) {

	   
	    
	    if (ind < 1 || (ind-1)>=plantas2.size()) {
	        return false;
	    }
	    
 if (!p.getNombrecomun().matches("[A-Z][a-zA-Z\\s]{2,99}")) {
	        
	        return false;
	    }

	    if (!p.getNombrecientifico().matches("[A-Z][a-zA-Z\\s]{2,99}")) {
	        
	        return false;
	    }
	    Planta plantaAntigua = plantaDAO.obtenerdatosplanta(p.getCodigo());
  if (p.getNombrecientifico().equals(plantaAntigua.getNombrecientifico()) &&p.getNombrecomun().equals(plantaAntigua.getNombrecomun ())) {
	        
	        return false;
	    }

		return true;
	}


	
	
	
}



