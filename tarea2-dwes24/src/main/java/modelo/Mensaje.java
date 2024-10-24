package modelo;

import java.time.LocalDateTime;
import java.util.Objects;

public class Mensaje {
    private Long id;
    private LocalDateTime fechaHora;
    private String mensaje;

   
    public Mensaje() {
    	super();
    }

    
    public Mensaje(Long id, LocalDateTime fechaHora, String mensaje) {
    	super();
        this.id = id;
        this.fechaHora = fechaHora;
        this.mensaje = mensaje;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }


	@Override
	public int hashCode() {
		return Objects.hash(fechaHora, id, mensaje);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mensaje other = (Mensaje) obj;
		return Objects.equals(fechaHora, other.fechaHora) && Objects.equals(id, other.id)
				&& Objects.equals(mensaje, other.mensaje);
	}


	@Override
	public String toString() {
		return "Mensaje [id=" + id + ", fechaHora=" + fechaHora + ", mensaje=" + mensaje + "]";
	}

   
}
