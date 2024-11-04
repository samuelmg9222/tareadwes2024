package principal;

public class Controlador {
	private static Controlador servicios;
	private ServicioPlanta servPlanta;
	private ServicioEjemplar servEjemplar;
	private ServicioPersona servPersona;
	private ServicioMensaje servMensaje;
	
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
}
