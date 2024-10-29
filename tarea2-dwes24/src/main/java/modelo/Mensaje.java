package modelo;

import java.time.LocalDateTime;
import java.util.Objects;

public class Mensaje {
    private Long id;
    private LocalDateTime fechaHora;
    private String mensaje;
    private long idEjemplar;
	private long idPersona;
   
    public Mensaje() {
    	super();
    }

    
    public Mensaje(Long id, LocalDateTime fechaHora, String mensaje, long idEjemplar, long idPersona) {
    	super();
        this.id = id;
        this.fechaHora = fechaHora;
        this.mensaje = mensaje;
        this.idEjemplar = idEjemplar;
		this.idPersona = idPersona;
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


    public long getIdEjemplar() {
		return idEjemplar;
	}

	public void setIdEjemplar(long idEjemplar) {
		this.idEjemplar = idEjemplar;
	}

	public long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(long idPersona) {
		this.idPersona = idPersona;
	}


	@Override
	public String toString() {
		return "Mensaje [id=" + id + ", fechaHora=" + fechaHora + ", mensaje=" + mensaje + ", idEjemplar=" + idEjemplar
				+ ", idPersona=" + idPersona + "]";
	}
	

   
}
