package principal;

import java.util.List;

import dao.PlantaDAO;
import modelo.Planta;

public class ServicioPlanta {

	
	private ConexionBD con;
		private PlantaDAO plantaDAO;
	public ServicioPlanta() {
		con=ConexionBD.getInstance();
		plantaDAO=(PlantaDAO) con.getPlantaDAO();
	}
	
	
	public boolean verificarPlanta(Planta p) {
	   
	    if (!p.getCodigo().matches("\\d{4}")) {
	        
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
        
        return false;
    
}
	return true;

}
public int modificarPlanta(Planta p) {
	return plantaDAO.modificarplantas(p);
	
}



public Planta obtenerdatosplanta(String codigo) {
	return plantaDAO.obtenerdatosplanta(codigo);
	
}



	public boolean verificarModificacion(Planta p) {
if (!p.getCodigo().matches("\\d{4}")) {
	        
	        return false;
	    }
	    if (!plantaDAO.existeCodigoPlanta(p.getCodigo())) {
	        
	        return false;
	    }
	    
 if (!p.getNombrecomun().matches("[A-Z][a-zA-Z\\s]{2,99}")) {
	        
	        return false;
	    }

	    if (!p.getNombrecientifico().matches("[A-Z][a-zA-Z\\s]{2,99}")) {
	        
	        return false;
	    }
	    Planta plantaAntigua = plantaDAO.obtenerdatosplanta(p.getCodigo());
  if (p.getNombrecientifico()==plantaAntigua.getNombrecientifico() &&p.getNombrecomun()==plantaAntigua.getNombrecomun ()) {
	        
	        return false;
	    }

		return true;
	}


	
	
	
}



