package modelo;

import java.util.Objects;

public class Persona {
    private Long id;
    private String nombre;
    private String email;
    private Credenciales credenciales; 
    
    public Persona() {
    	super();
    }

    
    public Persona(Long id, String nombre, String email) {
    	super();
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    // Getters y Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Credenciales getCredenciales() {
        return credenciales;
    }

    public void setCredenciales(Credenciales credenciales) {
        this.credenciales = credenciales;
    }


	

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", email=" + email + ", credenciales=" + credenciales + "]";
	}
}