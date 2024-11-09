package modelo;
import java.util.Objects;

public class Ejemplar {
    private Long id;
    private String nombre;

    
    
    
    public Ejemplar(Long id, String nombre) {
    	super();
        this.id = id;
        this.nombre = nombre;
    }

  
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   




	@Override
	public String toString() {
		return "Ejemplar [id=" + id + ", nombre=" + nombre + "]";
	}



}
 

