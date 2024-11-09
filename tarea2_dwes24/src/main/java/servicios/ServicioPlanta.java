package servicios;

import java.util.List;

import dao.PlantaDAO;
import modelo.Planta;
import utilidades.ConexionBD;

public class ServicioPlanta {

	private ConexionBD con;
	private PlantaDAO plantaDAO;

	
	public ServicioPlanta() {
		con = ConexionBD.getInstance();
		plantaDAO = (PlantaDAO) con.getPlantaDAO();
	}

	public int verificarPlanta(Planta p) {

		if (!p.getCodigo().matches("[A-Z]{4}")) {

			return -1;
		}
		if (plantaDAO.existeCodigoPlanta(p.getCodigo())) {

			return -2;
		}

		if (!p.getNombrecomun().matches("[A-Z][a-zA-Z\\s]{2,99}")) {

			return -3;
		}

		if (!p.getNombrecientifico().matches("[A-Z][a-zA-Z\\s]{2,99}")) {

			return -4;
		}
		return 1;
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

	public int verificarModificacion(Planta p, List<Planta> plantas2, int ind) {

		if (ind < 1 || (ind - 1) >= plantas2.size()) {
			return -1;
		}

		if (!p.getNombrecomun().matches("[A-Z][a-zA-Z\\s]{2,99}")) {

			return -2;
		}

		if (!p.getNombrecientifico().matches("[A-Z][a-zA-Z\\s]{2,99}")) {

			return -3;
		}
		Planta plantaAntigua = plantaDAO.obtenerdatosplanta(p.getCodigo());
		if (p.getNombrecientifico().equals(plantaAntigua.getNombrecientifico())
				&& p.getNombrecomun().equals(plantaAntigua.getNombrecomun())) {

			return -4;
		}

		return 1;
	}

}
