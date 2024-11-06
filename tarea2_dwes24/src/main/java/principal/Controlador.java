package principal;

import servicios.ServicioCredenciales;
import servicios.ServicioEjemplar;
import servicios.ServicioMensaje;
import servicios.ServicioPersona;
import servicios.ServicioPlanta;

public class Controlador {
	private static Controlador servicios;
	private ServicioPlanta servPlanta;
	private ServicioEjemplar servEjemplar;
	private ServicioPersona servPersona;
	private ServicioMensaje servMensaje;
	private 	ServicioCredenciales servCredenciales;
	public static Controlador getServicios() {
		if(servicios==null)
			servicios=new Controlador();
		return servicios;
		
	}

	private Controlador() {
		servPlanta=new ServicioPlanta();
		servEjemplar=new ServicioEjemplar();
		servPlanta=new ServicioPlanta();
		servPlanta=new ServicioPlanta();
		servMensaje=new ServicioMensaje();
		servCredenciales=new ServicioCredenciales();
	}
	public ServicioPlanta getServiciosPlanta() {
		return servPlanta;
	}
	public ServicioEjemplar getServiciosEjemplar() {
		return servEjemplar;
	}
	public ServicioPersona getServiciosPersona() {
		return servPersona;
	}
	public ServicioMensaje getServiciosMensaje() {
		return servMensaje;
	}
	public ServicioCredenciales getServiciosCredenciales() {
		return servCredenciales;
	}
}
